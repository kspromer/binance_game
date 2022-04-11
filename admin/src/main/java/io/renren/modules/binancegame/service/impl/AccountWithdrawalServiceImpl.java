package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateUtil;
import io.renren.common.validator.Assert;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AppAccountWithdrawalSaveDto;
import io.renren.modules.binancegame.entity.AccountEntity;
import io.renren.modules.binancegame.entity.MessageEntity;
import io.renren.modules.binancegame.entity.MoneyChangeEntity;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.enums.WithdrawalStatus;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import io.renren.modules.binancegame.service.AccountService;
import io.renren.modules.binancegame.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.AccountWithdrawalDao;
import io.renren.modules.binancegame.entity.AccountWithdrawalEntity;
import io.renren.modules.binancegame.dto.AccountWithdrawalDTO;
import io.renren.modules.binancegame.vo.AccountWithdrawalVO;
import io.renren.modules.binancegame.service.AccountWithdrawalService;
import io.renren.modules.binancegame.conver.AccountWithdrawalConver;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


@Service("accountWithdrawalService")
@BinanceGame
public class AccountWithdrawalServiceImpl extends ServiceImpl<AccountWithdrawalDao, AccountWithdrawalEntity> implements AccountWithdrawalService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public PageUtils<AccountWithdrawalVO> queryPage(AccountWithdrawalDTO accountWithdrawal) {
        IPage<AccountWithdrawalEntity> page = baseMapper.selectPage(
                new Query<AccountWithdrawalEntity>(accountWithdrawal).getPage(),
                new QueryWrapper<AccountWithdrawalEntity>().lambda()
                        .orderByDesc(AccountWithdrawalEntity::getId)
        );

        return PageUtils.<AccountWithdrawalVO>page(page).setList(AccountWithdrawalConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AccountWithdrawalVO getById(Long id) {
        return AccountWithdrawalConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(AccountWithdrawalDTO accountWithdrawal) {
        AccountWithdrawalEntity accountWithdrawalEntity = AccountWithdrawalConver.MAPPER.converDTO(accountWithdrawal);
        return this.save(accountWithdrawalEntity);
    }

    @Override
    public boolean updateById(AccountWithdrawalDTO accountWithdrawal) {
        AccountWithdrawalEntity accountWithdrawalEntity = AccountWithdrawalConver.MAPPER.converDTO(accountWithdrawal);
        return this.updateById(accountWithdrawalEntity);
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
    public void save(AppAccountWithdrawalSaveDto accountWithdrawal) {
//        1.保存审核记录
//        2.发送消息
//        3.修改用户余额
//        4.增加用户余额变动记录
        Long accountId = accountWithdrawal.getAccountId();
        BigDecimal money = accountWithdrawal.getMoney();
        AccountWithdrawalDTO dto = new AccountWithdrawalDTO();
        dto.setAccountId(accountId);
        dto.setMoney(money);
        dto.setAddress(accountWithdrawal.getAddress());
        dto.setCoinType(accountWithdrawal.getCoinType());
        dto.setStatus(WithdrawalStatus.ZERO.getKey());
        dto.setCreateTime(DateUtil.date());
        this.save(dto);
        //用户
        AccountVO accountVO = accountService.getById(accountId);
        //余额不足
        Assert.isTrue(accountVO.getMoney().compareTo(money) < 0,"There is not enough balance");
        MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
        moneyChangeMessageEvent.setAccountId(accountId);
        moneyChangeMessageEvent.setMoney(money.multiply(BigDecimal.valueOf(-1)));
        moneyChangeMessageEvent.setMessageType(MessageType.ZERO);
        moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.ZERO);
        moneyChangeMessageEvent.setAccountVO(accountVO);
        eventPublisher.publishEvent(moneyChangeMessageEvent);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void audit(AccountWithdrawalDTO accountWithdrawal) {
//        1.修改当前的审核状态
//        2.恢复当前用户余额
//        3.修改用户余额
//        给用户发送提现消息
        AccountWithdrawalVO accountWithdrawalVO = this.getById(accountWithdrawal.getId());
        Assert.isTrue(!accountWithdrawalVO.getStatus().equals(WithdrawalStatus.ZERO.getKey()),"当前状态不可以审核");
        this.updateById(accountWithdrawal);
        Long accountId = accountWithdrawal.getAccountId();
        //用户
        AccountVO accountVO = accountService.getById(accountId);

        MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
        moneyChangeMessageEvent.setAccountId(accountId);
        moneyChangeMessageEvent.setMessageType(MessageType.ONE);
        if (WithdrawalStatus.TWO.getKey().equals(accountWithdrawal.getStatus())) {
            moneyChangeMessageEvent.setMessageType(MessageType.TWO);
            moneyChangeMessageEvent.setMoney(accountWithdrawal.getMoney());
            moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.ONE);
            moneyChangeMessageEvent.setAccountVO(accountVO);
        }
        eventPublisher.publishEvent(moneyChangeMessageEvent);

    }

}
