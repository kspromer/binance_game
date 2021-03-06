package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.binance.client.SyncRequestClient;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.market.Candlestick;
import com.github.benmanes.caffeine.cache.Cache;
import io.renren.common.utils.Constant;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.vo.AppKlinesCurrentIssueNoVO;
import io.renren.modules.binancegame.entity.*;
import io.renren.modules.binancegame.enums.AgentCommissionId;
import io.renren.modules.binancegame.enums.KlinesState;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import io.renren.modules.binancegame.service.*;
import io.renren.modules.binancegame.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.KlinesDao;
import io.renren.modules.binancegame.dto.KlinesDTO;
import io.renren.modules.binancegame.vo.KlinesVO;
import io.renren.modules.binancegame.conver.KlinesConver;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("klinesService")
@BinanceGame
@Slf4j
public class KlinesServiceImpl extends ServiceImpl<KlinesDao, KlinesEntity> implements KlinesService {

    @Autowired
    private SyncRequestClient syncRequestClient;
    @Autowired
    private BetRecordService betRecordService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private Cache<String, AppKlinesCurrentIssueNoVO> cache;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private AgentCommissionService agentCommissionService;
    @Autowired
    private FranchiseConfigurationService franchiseConfigurationService;

    @Override
    public PageUtils<KlinesVO> queryPage(KlinesDTO klines) {
        IPage<KlinesEntity> page = baseMapper.selectPage(
                new Query<KlinesEntity>(klines).getPage(),
                new QueryWrapper<KlinesEntity>().lambda().orderByDesc(KlinesEntity::getId)
        );

        return PageUtils.<KlinesVO>page(page).setList(KlinesConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public KlinesVO getById(Long id) {
        return KlinesConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(KlinesDTO klines) {
        KlinesEntity klinesEntity = KlinesConver.MAPPER.converDTO(klines);
        return this.save(klinesEntity);
    }

    @Override
    public boolean updateById(KlinesDTO klines) {
        KlinesEntity klinesEntity = KlinesConver.MAPPER.converDTO(klines);
        return this.updateById(klinesEntity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public AppKlinesCurrentIssueNoVO currentIssueNo() {
        DateTime end = new DateTime();
        //??????????????????
        AppKlinesCurrentIssueNoVO vo = cache.getIfPresent("1");
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settlement() {
        List<KlinesEntity> list = this.list(new QueryWrapper<KlinesEntity>().lambda()
                .eq(KlinesEntity::getState,KlinesState.ZERO.getKey())
        );
        if (CollUtil.isEmpty(list)) {
            return;
        }
        //??????????????????
        FranchiseConfigurationEntity franchiseConfigurationEntity = franchiseConfigurationService.getById(1);
        for (KlinesEntity klinesEntity : list) {
            //????????????????????????????????????
            if (klinesEntity.getCloseTime().getTime() > System.currentTimeMillis()) {
                continue;
            }
            //???????????????
            List<Candlestick> btcusdt = syncRequestClient.getCandlestick(Constant.SYMBOL, CandlestickInterval.FIVE_MINUTES, klinesEntity.getOpenTime().getTime(), klinesEntity.getCloseTime().getTime(), 1);
            if (CollUtil.isEmpty(btcusdt)) {
                continue;
            }
            //????????????????????????
            Candlestick candlestick = btcusdt.get(0);
            KlinesDTO klinesDTO = new KlinesDTO();
            klinesDTO.setId(klinesEntity.getId());
            klinesDTO.setClose(candlestick.getClose());
            klinesDTO.setState(KlinesState.TWO.getKey());
            this.updateById(klinesDTO);
            //??????????????????
            List<BetRecordEntity> betRecordEntities = betRecordService.list(new QueryWrapper<BetRecordEntity>().lambda()
                    .eq(BetRecordEntity::getIssueNo,klinesEntity.getIssueNo())
            );
            //????????????
            String result = klinesDTO.getResult();
            //?????????????????????
            List<BetRecordEntity> updateBetRecordEntities = new ArrayList<>();
            //???????????????
            List<AccountEntity> updateAccountEntitys = new ArrayList<>();
            //????????????????????????map
            Map<Long, List<BetRecordEntity>> accountIdBetRecordEntities = betRecordEntities.stream().collect(Collectors.groupingBy(BetRecordEntity::getAccountId));
            accountIdBetRecordEntities.forEach((accountId,entities) -> {
                //??????????????????
                AccountVO accountVO = accountService.getById(accountId);
//                AccountEntity updateAccountEntity = new AccountEntity();
//                updateAccountEntity.setId(accountId);
                BigDecimal money = BigDecimal.ZERO;
                for (BetRecordEntity betRecordEntity : entities) {
                    BetRecordEntity update = new BetRecordEntity();
                    update.setId(betRecordEntity.getId());
                    update.setState(1);
                    if (!betRecordEntity.getPoint().equals(result) && franchiseConfigurationEntity.getMoney().doubleValue() <= betRecordEntity.getMoney().doubleValue()) {
                        betRecordEntity.setPoint(result);
                        update.setPoint(result);
                    }
                    //?????????????????????
                    if (betRecordEntity.getPoint().equals(result)) {
                        update.setResult(betRecordEntity.getMoney().multiply(BigDecimal.valueOf(-1)));
                        updateBetRecordEntities.add(update);
                        continue;
                    }
                    BigDecimal betRecordResult = betRecordEntity.getMoney().multiply(betRecordEntity.getOdds());
                    update.setResult(betRecordResult);
                    money = money.add(update.getResult());
                    updateBetRecordEntities.add(update);
                }
                MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
                moneyChangeMessageEvent.setAccountId(accountId);
                moneyChangeMessageEvent.setMoney(money);
                moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.TWO);
                moneyChangeMessageEvent.setAccountVO(accountVO);
                eventPublisher.publishEvent(moneyChangeMessageEvent);
            });

            //??????????????????
            if (CollUtil.isNotEmpty(betRecordEntities)) {
                //?????????????????????
                betRecordService.updateBatchById(updateBetRecordEntities);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void agentSettlement() {
        List<KlinesEntity> list = this.list(new QueryWrapper<KlinesEntity>().lambda()
                .eq(KlinesEntity::getState,KlinesState.TWO.getKey())
        );
        if (CollUtil.isEmpty(list)) {
            return;
        }
        //??????????????????
        AgentCommissionEntity agentCommissionEntity1 = agentCommissionService.getById(AgentCommissionId.ONE.getKey());
        AgentCommissionEntity agentCommissionEntity2 = agentCommissionService.getById(AgentCommissionId.TWO.getKey());
        if (ObjectUtil.isNull(agentCommissionEntity1) || ObjectUtil.isNull(agentCommissionEntity2)) {
            return;
        }
        //???????????? ???????????????
        for (KlinesEntity klinesEntity : list) {
            //???????????????????????????
            KlinesEntity klinesDTO = new KlinesEntity();
            klinesDTO.setId(klinesEntity.getId());
            klinesDTO.setState(KlinesState.THREE.getKey());
            this.updateById(klinesDTO);

            //??????????????????
            List<BetRecordEntity> betRecordEntities = betRecordService.list(new QueryWrapper<BetRecordEntity>().lambda()
                    .eq(BetRecordEntity::getIssueNo,klinesEntity.getIssueNo())
            );
            //????????????
            String result = klinesDTO.getResult();
            //????????????????????????map
            Map<Long, List<BetRecordEntity>> accountIdBetRecordEntities = betRecordEntities.stream().collect(Collectors.groupingBy(BetRecordEntity::getAccountId));
            accountIdBetRecordEntities.forEach((accountId,entities) -> {
                //??????????????????
                AccountVO accountVO = accountService.getById(accountId);
                //????????????
                BigDecimal money = BigDecimal.ZERO;
                for (BetRecordEntity betRecordEntity : entities) {
                    //?????????????????????
                    if (betRecordEntity.getPoint().equals(result)) {
                        continue;
                    }
                    money = money.add(betRecordEntity.getMoney());
                }
                if (ObjectUtil.isNull(accountVO)) {
                    return;
                }
                //??????????????????
                AccountVO accountVOUpper = accountService.getById(accountVO.getUpper());
                if (ObjectUtil.isNull(accountVOUpper)) {
                    return;
                }
                BigDecimal moneyUpper = agentCommissionEntity1.getProportion().multiply(money);
                MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
                moneyChangeMessageEvent.setAccountId(accountVOUpper.getId());
                moneyChangeMessageEvent.setMoney(moneyUpper);
                moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.FOUR);
                moneyChangeMessageEvent.setAccountVO(accountVOUpper);
                eventPublisher.publishEvent(moneyChangeMessageEvent);
                //?????????????????????
                AccountVO accountVOUpperUpper = accountService.getById(accountVOUpper.getUpper());
                if (ObjectUtil.isNull(accountVOUpperUpper)) {
                    return;
                }
                BigDecimal moneyUpperUpper = agentCommissionEntity2.getProportion().multiply(money);
                MoneyChangeMessageEvent moneyChangeMessageEvent2 = new MoneyChangeMessageEvent(this);
                moneyChangeMessageEvent2.setAccountId(accountVOUpperUpper.getId());
                moneyChangeMessageEvent2.setMoney(moneyUpperUpper);
                moneyChangeMessageEvent2.setMoneyChangeType(MoneyChangeType.FOUR);
                moneyChangeMessageEvent2.setAccountVO(accountVOUpperUpper);
                eventPublisher.publishEvent(moneyChangeMessageEvent2);
            });
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void currentIssueNoCacheRefresh() {
        this.currentIssueNoCache();
    }


    /**
     * ????????????????????????
     * @param event
     */
    @EventListener
    @Order(value = 9999)
    @Transactional(rollbackFor = Exception.class)
    public void handlerApplicationReadyEvent(ApplicationReadyEvent event) {
        refreshKlines();
    }


    /**
     * ?????????????????????????????????????????????
     * @param event
     */
    @EventListener
    @Order(value = 9999)
    @Transactional(rollbackFor = Exception.class)
    public void handlerApplicationReadyEvent1(ApplicationReadyEvent event) {
        currentIssueNoCache();
    }

    private void currentIssueNoCache() {
        DateTime end = new DateTime();
        boolean flag = true;
        while (flag) {
            //???????????????????????????
            List<Candlestick> btcusdt = syncRequestClient.getCandlestick(Constant.SYMBOL, CandlestickInterval.FIVE_MINUTES, null, end.getTime(), 1);
            if (CollUtil.isNotEmpty(btcusdt)) {
                Candlestick candlestick = btcusdt.get(0);
                if (candlestick.getCloseTime() < System.currentTimeMillis()) {
                    continue;
                }
                AppKlinesCurrentIssueNoVO vo = new AppKlinesCurrentIssueNoVO();
                vo.setOpenTime(candlestick.getOpenTime());
                vo.setCloseTime(candlestick.getCloseTime());
                vo.setClose(candlestick.getClose());
                vo.setIssueNo(vo.getIssueNo());
                vo.setSymbol(Constant.SYMBOL);
                vo.setResult(vo.getResult());
                vo.setState(KlinesState.TWO.getKey());
                cache.put("1",vo);
                this.refreshKlines();
                flag = false;
            }
        }
    }

    private void refreshKlines() {
        DateTime end = DateUtil.offsetSecond(DateUtil.date(),5);
        List<Candlestick> btcusdt = syncRequestClient.getCandlestick(Constant.SYMBOL, CandlestickInterval.FIVE_MINUTES, null, end.getTime(), 70);

        List<KlinesDTO> dtos = new ArrayList<>();

        for (Candlestick candlestick : btcusdt) {
            KlinesDTO dto = new KlinesDTO();
            dto.setOpenTime(DateUtil.date(candlestick.getOpenTime()));
            dto.setCloseTime(DateUtil.date(candlestick.getCloseTime()));
            dto.setClose(candlestick.getClose());
            dto.setIssueNo(dto.getIssueNo());
            dto.setSymbol(Constant.SYMBOL);
            dto.setResult(dto.getResult());
            dto.setState(KlinesState.TWO.getKey());
            KlinesEntity one = this.getOne(new QueryWrapper<KlinesEntity>().lambda()
                    .eq(KlinesEntity::getIssueNo,dto.getIssueNo())
            );
            if (ObjectUtil.isNotNull(one)) {
                continue;
            }
            if (System.currentTimeMillis() > candlestick.getCloseTime()) {

            }else {
                dto.setState(KlinesState.ZERO.getKey());
            }
            dtos.add(dto);
        }
        this.saveBatch(KlinesConver.MAPPER.converDTO(dtos));
    }
}
