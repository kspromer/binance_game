package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.binance.client.model.trade.Asset;
import io.renren.common.validator.Assert;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AppBetRecordBetDTO;
import io.renren.modules.app.dto.AppBetRecordListDTO;
import io.renren.modules.app.vo.AppBetRecordListIssueVO;
import io.renren.modules.app.vo.AppBetRecordListVO;
import io.renren.modules.app.vo.AppKlinesCurrentIssueNoVO;
import io.renren.modules.binancegame.entity.AccountEntity;
import io.renren.modules.binancegame.entity.BetConfigEntity;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import io.renren.modules.binancegame.service.AccountService;
import io.renren.modules.binancegame.service.BetConfigService;
import io.renren.modules.binancegame.service.KlinesService;
import io.renren.modules.binancegame.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.BetRecordDao;
import io.renren.modules.binancegame.entity.BetRecordEntity;
import io.renren.modules.binancegame.dto.BetRecordDTO;
import io.renren.modules.binancegame.vo.BetRecordVO;
import io.renren.modules.binancegame.service.BetRecordService;
import io.renren.modules.binancegame.conver.BetRecordConver;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.renren.common.utils.Constant.SYMBOL;


@Service("betRecordService")
@BinanceGame
@Slf4j
public class BetRecordServiceImpl extends ServiceImpl<BetRecordDao, BetRecordEntity> implements BetRecordService {

    @Autowired
    private BetConfigService betConfigService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private KlinesService klinesService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public PageUtils<BetRecordVO> queryPage(BetRecordDTO betRecord) {
        IPage<BetRecordEntity> page = baseMapper.selectPage(
                new Query<BetRecordEntity>(betRecord).getPage(),
                new QueryWrapper<BetRecordEntity>()
        );

        return PageUtils.<BetRecordVO>page(page).setList(BetRecordConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public BetRecordVO getById(Long id) {
        return BetRecordConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(BetRecordDTO betRecord) {
        BetRecordEntity betRecordEntity = BetRecordConver.MAPPER.converDTO(betRecord);
        return this.save(betRecordEntity);
    }

    @Override
    public boolean updateById(BetRecordDTO betRecord) {
        BetRecordEntity betRecordEntity = BetRecordConver.MAPPER.converDTO(betRecord);
        return this.updateById(betRecordEntity);
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
    @Transactional(rollbackFor = Exception.class)
    public void bet(AppBetRecordBetDTO dto) {
        //获取当前账户
        AccountVO accountVO = accountService.getById(dto.getAccountId());
        Assert.isNull(accountVO,"账号不存在");
        Assert.isTrue(accountVO.getMoney().compareTo(dto.getMoney()) < 0,"余额不足");
        //获取所有点数
        List<BetConfigEntity> list = betConfigService.list();
        //获取当前选中的点数
        BetConfigEntity pointBetConfigEntity = list.stream().filter(betConfigEntity -> betConfigEntity.getPoint().equals(dto.getPoint())).findFirst().orElse(null);
        //获取当前的期号
        AppKlinesCurrentIssueNoVO klinesCurrentIssueNoVO = klinesService.currentIssueNo();
        Assert.isTrue(klinesCurrentIssueNoVO.getCountdown() < 20,"当前期号投注已截止");
        Assert.isNull(pointBetConfigEntity,"点数不存在");
        BetRecordEntity betRecordEntity = new BetRecordEntity();
        betRecordEntity.setAccountId(dto.getAccountId());
        betRecordEntity.setMoney(dto.getMoney());
        betRecordEntity.setPoint(dto.getPoint());
        betRecordEntity.setIssueNo(klinesCurrentIssueNoVO.getIssueNo());
        betRecordEntity.setCreateTime(DateUtil.date());
        betRecordEntity.setState(0);
        betRecordEntity.setSymbol(SYMBOL);
        betRecordEntity.setOdds(pointBetConfigEntity.getOdds());
        this.save(betRecordEntity);
        //修改用户余额
//        BigDecimal beforeMoney = accountVO.getMoney();
//        BigDecimal afterMoney = beforeMoney.subtract(betRecordEntity.getMoney());
//        AccountEntity accountEntity = new AccountEntity();
//        accountEntity.setId(accountVO.getId());
//        accountEntity.setMoney(afterMoney);
//        accountService.updateById(accountEntity);
        MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
        moneyChangeMessageEvent.setAccountId(dto.getAccountId());
        moneyChangeMessageEvent.setMoney(betRecordEntity.getMoney().multiply(BigDecimal.valueOf(-1)));
        moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.THREE);
        moneyChangeMessageEvent.setAccountVO(accountVO);
        eventPublisher.publishEvent(moneyChangeMessageEvent);
    }

    @Override
    public PageUtils listPage(AppBetRecordListDTO dto) {
        IPage<AppBetRecordListIssueVO> page = baseMapper.listPage(
                new Query<BetRecordEntity>(dto).getPage(),
                dto
        );
        return PageUtils.<BetRecordVO>page(page);
    }

}
