package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateUtil;
import io.renren.common.validator.Assert;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AppAccountTranslationTranslationDTO;
import io.renren.modules.binancegame.conver.AccountConver;
import io.renren.modules.binancegame.entity.AccountEntity;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
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

import io.renren.modules.binancegame.dao.AccountTranslationDao;
import io.renren.modules.binancegame.entity.AccountTranslationEntity;
import io.renren.modules.binancegame.dto.AccountTranslationDTO;
import io.renren.modules.binancegame.vo.AccountTranslationVO;
import io.renren.modules.binancegame.service.AccountTranslationService;
import io.renren.modules.binancegame.conver.AccountTranslationConver;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


@Service("accountTranslationService")
@BinanceGame
public class AccountTranslationServiceImpl extends ServiceImpl<AccountTranslationDao, AccountTranslationEntity> implements AccountTranslationService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public PageUtils<AccountTranslationVO> queryPage(AccountTranslationDTO accountTranslation) {
        IPage<AccountTranslationEntity> page = baseMapper.selectPage(
                new Query<AccountTranslationEntity>(accountTranslation).getPage(),
                new QueryWrapper<AccountTranslationEntity>()
        );

        return PageUtils.<AccountTranslationVO>page(page).setList(AccountTranslationConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AccountTranslationVO getById(Long id) {
        return AccountTranslationConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(AccountTranslationDTO accountTranslation) {
        AccountTranslationEntity accountTranslationEntity = AccountTranslationConver.MAPPER.converDTO(accountTranslation);
        return this.save(accountTranslationEntity);
    }

    @Override
    public boolean updateById(AccountTranslationDTO accountTranslation) {
        AccountTranslationEntity accountTranslationEntity = AccountTranslationConver.MAPPER.converDTO(accountTranslation);
        return this.updateById(accountTranslationEntity);
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
    public void translation(AppAccountTranslationTranslationDTO dto) {
        BigDecimal money = dto.getMoney();
        //被转账的用户
        AccountEntity toUsername = accountService.getOne(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getUsername,dto.getToUsername())
        );
        Assert.isNull(toUsername,"User does not exist");
        //转账的用户
        AccountVO accountVO = accountService.getById(dto.getAccountId());
        Assert.isNull(accountVO,"User does not exist");
        Assert.isTrue(accountVO.getMoney().compareTo(money) < 0,"There is not enough balance");
        //保存转账记录
        AccountTranslationDTO accountTranslationDTO = new AccountTranslationDTO();
        accountTranslationDTO.setAccountId(dto.getAccountId());
        accountTranslationDTO.setToAccountId(toUsername.getId());
        accountTranslationDTO.setToUsername(toUsername.getUsername());
        accountTranslationDTO.setMoney(dto.getMoney());
        accountTranslationDTO.setNote(dto.getNote());
        accountTranslationDTO.setCreateTime(DateUtil.date());
        this.save(accountTranslationDTO);
        //转账的用户
        MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
        moneyChangeMessageEvent.setAccountId(dto.getAccountId());
        moneyChangeMessageEvent.setMoney(money.multiply(BigDecimal.valueOf(-1)));
        moneyChangeMessageEvent.setMessageType(MessageType.THREE);
        moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.FIVE);
        moneyChangeMessageEvent.setAccountVO(accountVO);
        eventPublisher.publishEvent(moneyChangeMessageEvent);
        //被转账的用户
        MoneyChangeMessageEvent moneyChangeMessageEventto = new MoneyChangeMessageEvent(this);
        moneyChangeMessageEventto.setAccountId(toUsername.getId());
        moneyChangeMessageEventto.setMoney(money);
        moneyChangeMessageEventto.setMessageType(MessageType.THREE);
        moneyChangeMessageEventto.setMoneyChangeType(MoneyChangeType.FIVE);
        moneyChangeMessageEventto.setAccountVO(AccountConver.MAPPER.conver(toUsername));
        eventPublisher.publishEvent(moneyChangeMessageEventto);
    }

}
