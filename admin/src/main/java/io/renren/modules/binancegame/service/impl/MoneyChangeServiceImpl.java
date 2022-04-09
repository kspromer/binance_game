package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AccountRebateRecordDTO;
import io.renren.modules.app.vo.AccountRebateRecordVO;
import io.renren.modules.binancegame.entity.MessageEntity;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import io.renren.modules.binancegame.vo.AccountVO;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.MoneyChangeDao;
import io.renren.modules.binancegame.entity.MoneyChangeEntity;
import io.renren.modules.binancegame.dto.MoneyChangeDTO;
import io.renren.modules.binancegame.vo.MoneyChangeVO;
import io.renren.modules.binancegame.service.MoneyChangeService;
import io.renren.modules.binancegame.conver.MoneyChangeConver;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


@Service("moneyChangeService")
@BinanceGame
public class MoneyChangeServiceImpl extends ServiceImpl<MoneyChangeDao, MoneyChangeEntity> implements MoneyChangeService {

    @Override
    public PageUtils<MoneyChangeVO> queryPage(MoneyChangeDTO moneyChange) {
        IPage<MoneyChangeEntity> page = baseMapper.selectPage(
                new Query<MoneyChangeEntity>(moneyChange).getPage(),
                new QueryWrapper<MoneyChangeEntity>()
        );

        return PageUtils.<MoneyChangeVO>page(page).setList(MoneyChangeConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public MoneyChangeVO getById(Long id) {
        return MoneyChangeConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(MoneyChangeDTO moneyChange) {
        MoneyChangeEntity moneyChangeEntity = MoneyChangeConver.MAPPER.converDTO(moneyChange);
        return this.save(moneyChangeEntity);
    }

    @Override
    public boolean updateById(MoneyChangeDTO moneyChange) {
        MoneyChangeEntity moneyChangeEntity = MoneyChangeConver.MAPPER.converDTO(moneyChange);
        return this.updateById(moneyChangeEntity);
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
    public BigDecimal totalCommission(Long accountId) {
        return baseMapper.totalCommission(accountId);
    }

    @Override
    public Object rebateRecord(AccountRebateRecordDTO accountRebateRecordDTO) {
        IPage<MoneyChangeEntity> page = baseMapper.selectPage(
                new Query<MoneyChangeEntity>(accountRebateRecordDTO).getPage(),
                new QueryWrapper<MoneyChangeEntity>().lambda()
                        .eq(MoneyChangeEntity::getAccountId,accountRebateRecordDTO.getAccountId())
                        .orderByDesc(MoneyChangeEntity::getId)
                        .eq(MoneyChangeEntity::getType,MoneyChangeType.FOUR.getKey())
        );
        return PageUtils.<AccountRebateRecordVO>page(page).setList(MoneyChangeConver.MAPPER.converAccountRebateRecordVO(page.getRecords()));
    }

    /**
     * 刷新token监听器
     * @param event
     */
    @EventListener
    @Order(200)
    public void handlerMoneyChangeMessageEvent(MoneyChangeMessageEvent event) {
        AccountVO accountVO = event.getAccountVO();
        BigDecimal money = event.getMoney();
        Long accountId = event.getAccountId();
        MoneyChangeType moneyChangeType = event.getMoneyChangeType();
        if(ObjectUtil.isNull(accountVO)) {
            return;
        }
        //之前的余额
        BigDecimal before = accountVO.getMoney();
        //之后的余额
        BigDecimal after = before.add(money);
        if (ObjectUtil.isNotNull(moneyChangeType)) {
            //金额变动
            MoneyChangeEntity moneyChangeEntity = new MoneyChangeEntity();
            moneyChangeEntity.setCreateTime(DateUtil.date());
            moneyChangeEntity.setType(moneyChangeType.getKey());
            moneyChangeEntity.setDescription(moneyChangeType.getValue());
            moneyChangeEntity.setAmount(money);
            moneyChangeEntity.setAccountId(accountId);
            moneyChangeEntity.setBeforeMoney(before);
            moneyChangeEntity.setAfterMoney(after);
            this.save(moneyChangeEntity);
        }
    }
}
