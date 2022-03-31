package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RadixUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.validator.Assert;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AppAccountLoginDTO;
import io.renren.modules.app.dto.AppAccountRegisterDTO;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.binancegame.conver.AccountConver;
import io.renren.modules.binancegame.dao.AccountDao;
import io.renren.modules.binancegame.dto.AccountDTO;
import io.renren.modules.binancegame.entity.AccountEntity;
import io.renren.modules.binancegame.entity.MessageEntity;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import io.renren.modules.binancegame.service.AccountCaptchaService;
import io.renren.modules.binancegame.service.AccountService;
import io.renren.modules.binancegame.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


@Service("accountService")
@BinanceGame
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {

    @Autowired
    private AccountCaptchaService sysCaptchaService;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public PageUtils<AccountVO> queryPage(AccountDTO account) {
        IPage<AccountEntity> page = baseMapper.selectPage(
                new Query<AccountEntity>(account).getPage(),
                new QueryWrapper<AccountEntity>()
        );

        return PageUtils.<AccountVO>page(page).setList(AccountConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AccountVO getById(Long id) {
        return AccountConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(AccountDTO account) {
        AccountEntity accountEntity = AccountConver.MAPPER.converDTO(account);
        return this.save(accountEntity);
    }

    @Override
    public boolean updateById(AccountDTO account) {
        AccountEntity accountEntity = AccountConver.MAPPER.converDTO(account);
        return this.updateById(accountEntity);
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
    public void register(AppAccountRegisterDTO dto) {
        //查询用户是否存在
        AccountEntity one = this.getOne(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getUsername,dto.getUsername())
                .last("limit 1")
                .orderByDesc(AccountEntity::getId)
        );
        Assert.isTrue(ObjectUtil.isNotNull(one),"username already exists");
        AccountEntity inviteCodeOne = null;
        //如果传了邀请人,判断邀请人是否存在
        if (StrUtil.isNotEmpty(dto.getInviteCode().trim())) {
            inviteCodeOne = this.getOne(new QueryWrapper<AccountEntity>().lambda()
                    .eq(AccountEntity::getInviteCode,dto.getInviteCode())
                    .last("limit 1")
                    .orderByDesc(AccountEntity::getId)
            );
            Assert.isTrue(ObjectUtil.isNull(inviteCodeOne),"Inviter does not exist");
        }
        boolean captcha = sysCaptchaService.validate(dto.getUuid(), dto.getCode());
        Assert.isTrue(!captcha,"verification code is incorrect");
        AccountEntity accountDTO = new AccountEntity();
        accountDTO.setCreateTime(DateUtil.date());
        accountDTO.setUsername(dto.getUsername());
        accountDTO.setPassword(dto.getPassword());
        if (ObjectUtil.isNotNull(inviteCodeOne)) {
            accountDTO.setUpper(inviteCodeOne.getId());
        }
        this.save(accountDTO);
        //邀请码
        String inviteCode = String.format("%s%s", RadixUtil.encode(RadixUtil.RADIXS_SHUFFLE_59,accountDTO.getId()),RadixUtil.encode(RadixUtil.RADIXS_SHUFFLE_34,accountDTO.getId()));
        accountDTO.setInviteCode(inviteCode);
        this.updateById(accountDTO);
    }

    @Override
    public String login(AppAccountLoginDTO dto) {
        AccountEntity one = this.getOne(new QueryWrapper<AccountEntity>().lambda()
                .eq(AccountEntity::getUsername,dto.getUsername())
        );
        Assert.isTrue(ObjectUtil.isNull(one),"Account does not exist.");
        Assert.isTrue(!one.getPassword().equals(dto.getPassword()),"Password mistake");
        //获取用户id生成token
        String token = jwtUtils.generateToken(one.getId());
        return token;
    }

    /**
     * 刷新token监听器
     * @param event
     */
    @EventListener
    @Order(300)
    public void handlerMoneyChangeMessageEvent(MoneyChangeMessageEvent event) {
        AccountVO accountVO = event.getAccountVO();
        BigDecimal money = event.getMoney();
        Long accountId = event.getAccountId();
        if(ObjectUtil.isNull(accountVO)) {
            return;
        }
        //之前的余额
        BigDecimal before = accountVO.getMoney();
        //之后的余额
        BigDecimal after = before.add(money);
        MoneyChangeType moneyChangeType = event.getMoneyChangeType();
        if (ObjectUtil.isNotNull(moneyChangeType)) {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(accountId);
            accountEntity.setMoney(after);
            this.updateById(accountEntity);
        }
    }

}
