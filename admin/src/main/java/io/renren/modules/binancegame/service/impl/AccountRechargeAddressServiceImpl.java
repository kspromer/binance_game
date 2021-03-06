package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.renren.common.solidity.BscUsdt;
import io.renren.common.utils.wallet.WalletClient;
import io.renren.config.bsc.Web3jConfig;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AccountRechargeAddressGetAddressDto;
import io.renren.modules.app.vo.AccountRechargeAddressGetAddressVO;
import io.renren.modules.binancegame.enums.CoinType;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import io.renren.modules.binancegame.service.AccountService;
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

import io.renren.modules.binancegame.dao.AccountRechargeAddressDao;
import io.renren.modules.binancegame.entity.AccountRechargeAddressEntity;
import io.renren.modules.binancegame.dto.AccountRechargeAddressDTO;
import io.renren.modules.binancegame.vo.AccountRechargeAddressVO;
import io.renren.modules.binancegame.service.AccountRechargeAddressService;
import io.renren.modules.binancegame.conver.AccountRechargeAddressConver;
import org.springframework.transaction.annotation.Transactional;
import org.tron.trident.core.contract.Trc20Contract;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("accountRechargeAddressService")
@BinanceGame
@Slf4j
public class AccountRechargeAddressServiceImpl extends ServiceImpl<AccountRechargeAddressDao, AccountRechargeAddressEntity> implements AccountRechargeAddressService {

    @Autowired
    private WalletClient walletClient;
    @Autowired
    private Web3j web3j;
    @Autowired
    private Web3jConfig web3jConfig;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private StaticGasProvider staticGasProvider;
    @Autowired
    private AccountService accountService;
    @Autowired
    private Trc20Contract token;

    @Override
    public PageUtils<AccountRechargeAddressVO> queryPage(AccountRechargeAddressDTO accountRechargeAddress) {
        IPage<AccountRechargeAddressEntity> page = baseMapper.selectPage(
                new Query<AccountRechargeAddressEntity>(accountRechargeAddress).getPage(),
                new QueryWrapper<AccountRechargeAddressEntity>().lambda()
                        .eq(ObjectUtil.isNotNull(accountRechargeAddress.getAccountId()),AccountRechargeAddressEntity::getAccountId,accountRechargeAddress.getAccountId())
        );

        return PageUtils.<AccountRechargeAddressVO>page(page).setList(AccountRechargeAddressConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AccountRechargeAddressVO getById(Long id) {
        return AccountRechargeAddressConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(AccountRechargeAddressDTO accountRechargeAddress) {
        //???????????????????????????
        if (StrUtil.isEmpty(accountRechargeAddress.getPrivateKey())) {
            accountRechargeAddress.setPrivateKey(walletClient.generateMnemonic().stream().collect(Collectors.joining(" ")));
        }
        Integer walletSerialNumber = accountRechargeAddress.getWalletSerialNumber();
        List<AccountRechargeAddressEntity> accountRechargeAddressEntities = new ArrayList<>();
        for (Integer i = 0; i < walletSerialNumber; i++) {
            ECKeyPair ecKeyPair = walletClient.createECKeyPair(accountRechargeAddress.getPrivateKey(), i);
            if (CoinType.ONE.getValue().equals(accountRechargeAddress.getCoinType())) {
                ecKeyPair = walletClient.createECKeyPairTrx(accountRechargeAddress.getPrivateKey(), i);
            }
            AccountRechargeAddressEntity accountRechargeAddressEntity = new AccountRechargeAddressEntity();
            accountRechargeAddressEntity.setAccountId(-1L);
            accountRechargeAddressEntity.setAddress(walletClient.getAddress(ecKeyPair));
            if (CoinType.ONE.getValue().equals(accountRechargeAddress.getCoinType())) {
                accountRechargeAddressEntity.setAddress(walletClient.getAddressTrx(ecKeyPair));
            }
            accountRechargeAddressEntity.setPrivateKey(accountRechargeAddress.getPrivateKey());
            accountRechargeAddressEntity.setWalletSerialNumber(i+1);
            accountRechargeAddressEntity.setCoinType(accountRechargeAddress.getCoinType());
            accountRechargeAddressEntity.setCreateTime(DateUtil.date());
            accountRechargeAddressEntities.add(accountRechargeAddressEntity);
        }
        return this.saveBatch(accountRechargeAddressEntities);
    }

    @Override
    public boolean updateById(AccountRechargeAddressDTO accountRechargeAddress) {
        AccountRechargeAddressEntity accountRechargeAddressEntity = AccountRechargeAddressConver.MAPPER.converDTO(accountRechargeAddress);
        return this.updateById(accountRechargeAddressEntity);
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
    public List<AccountRechargeAddressGetAddressVO> getAddress(AccountRechargeAddressGetAddressDto dto) {
        List<AccountRechargeAddressGetAddressVO> accountRechargeAddressGetAddressVOS = new ArrayList<>();
        CoinType[] values = CoinType.values();
        //??????????????????
        for (CoinType value : values) {
            AccountRechargeAddressEntity one = this.getOne(new QueryWrapper<AccountRechargeAddressEntity>().lambda()
                    .eq(AccountRechargeAddressEntity::getAccountId,dto.getAccountId())
                    .eq(AccountRechargeAddressEntity::getCoinType,value.getValue())
                    .last("limit 1")
            );
            if (ObjectUtil.isNull(one)) {
                one = this.getOne(new QueryWrapper<AccountRechargeAddressEntity>().lambda()
                        .eq(AccountRechargeAddressEntity::getAccountId,-1)
                        .eq(AccountRechargeAddressEntity::getCoinType,value.getValue())
                        .last("limit 1")
                );
            }
            if (ObjectUtil.isNotNull(one) && CoinType.ZERO.getValue().equals(value.getValue())) {
                ECKeyPair ecKeyPair = walletClient.createECKeyPair(one.getPrivateKey(), one.getWalletSerialNumber());
                BscUsdt bscUsdt = BscUsdt.load(web3jConfig.getContractAddress(), web3j, Credentials.create(ecKeyPair), staticGasProvider);
                try {
                    BigInteger balanceOf = bscUsdt.balanceOf(one.getAddress()).send();
                    one.setBalanceOf(balanceOf);
                } catch (Exception e) {
                    log.error("e = {}",e);
                }
                one.setAccountId(dto.getAccountId());
                one.setCreateTime(DateUtil.date());
                this.updateById(one);
                AccountRechargeAddressGetAddressVO vo = new AccountRechargeAddressGetAddressVO();
                vo.setAddress(one.getAddress());
                vo.setCoinType(one.getCoinType());
                accountRechargeAddressGetAddressVOS.add(vo);
            }else if (ObjectUtil.isNotNull(one) && CoinType.ONE.getValue().equals(value.getValue())) {
                BigInteger balanceOf = token.balanceOf(one.getAddress());
                one.setBalanceOf(balanceOf);
                one.setAccountId(dto.getAccountId());
                one.setCreateTime(DateUtil.date());
                this.updateById(one);
                AccountRechargeAddressGetAddressVO vo = new AccountRechargeAddressGetAddressVO();
                vo.setAddress(one.getAddress());
                vo.setCoinType(one.getCoinType());
                accountRechargeAddressGetAddressVOS.add(vo);
            }
        }
        return accountRechargeAddressGetAddressVOS;
    }

    /**
     * ??????USDT??????
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rechargeTask() {
        DateTime dateTime = DateUtil.offsetMinute(DateUtil.date(), -10);
        //???????????????????????????????????????
        List<AccountRechargeAddressEntity> list = this.list(new QueryWrapper<AccountRechargeAddressEntity>().lambda()
                .gt(AccountRechargeAddressEntity::getAccountId,0)
                .gt(AccountRechargeAddressEntity::getCreateTime,dateTime)
        );
        log.info("AccountRechargeAddressEntity = {}",list.size());
        for (AccountRechargeAddressEntity one : list) {
            //BEP20??????
            if (CoinType.ZERO.getValue().equals(one.getCoinType())) {
                ECKeyPair ecKeyPair = walletClient.createECKeyPair(one.getPrivateKey(), one.getWalletSerialNumber());
                BscUsdt bscUsdt = BscUsdt.load(web3jConfig.getContractAddress(), web3j, Credentials.create(ecKeyPair), staticGasProvider);
                try {
                    //???????????????
                    BigInteger balanceOf = bscUsdt.balanceOf(one.getAddress()).send();
                    //??????????????????????????????,??????????????????
                    if (one.getBalanceOf().compareTo(balanceOf) == 0) {
                        continue;
                    }
                    //????????????
                    BigDecimal currentBalanceOf = Convert.fromWei(balanceOf.toString(), Convert.Unit.ETHER);
                    //????????????
                    BigDecimal beforeBalanceOf = Convert.fromWei(one.getBalanceOf().toString(), Convert.Unit.ETHER);
                    if (currentBalanceOf.doubleValue() < beforeBalanceOf.doubleValue()) {
                        continue;
                    }
                    AccountVO accountVO = accountService.getById(one.getAccountId());
                    if (ObjectUtil.isNull(accountVO)) {
                        continue;
                    }
                    //???????????????
                    BigDecimal money = currentBalanceOf.subtract(beforeBalanceOf);

                    MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
                    moneyChangeMessageEvent.setAccountId(one.getAccountId());
                    moneyChangeMessageEvent.setMoney(money);
                    moneyChangeMessageEvent.setMessageType(MessageType.FIVE);
                    moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.SEVEN);
                    moneyChangeMessageEvent.setAccountVO(accountVO);
                    eventPublisher.publishEvent(moneyChangeMessageEvent);
                    one.setBalanceOf(balanceOf);
                    this.updateById(one);
                } catch (Exception e) {
                    log.error("e = {}",e);
                }
            }else if (CoinType.ONE.getValue().equals(one.getCoinType())) {
                try {
                    //???????????????
                    BigInteger balanceOf = token.balanceOf(one.getAddress());
                    //??????????????????????????????,??????????????????
                    if (one.getBalanceOf().compareTo(balanceOf) == 0) {
                        continue;
                    }
                    //????????????
                    BigDecimal currentBalanceOf = Convert.fromWei(balanceOf.toString(), Convert.Unit.MWEI);
                    //????????????
                    BigDecimal beforeBalanceOf = Convert.fromWei(one.getBalanceOf().toString(), Convert.Unit.MWEI);
                    if (currentBalanceOf.doubleValue() < beforeBalanceOf.doubleValue()) {
                        continue;
                    }
                    AccountVO accountVO = accountService.getById(one.getAccountId());
                    if (ObjectUtil.isNull(accountVO)) {
                        continue;
                    }
                    //???????????????
                    BigDecimal money = currentBalanceOf.subtract(beforeBalanceOf);

                    MoneyChangeMessageEvent moneyChangeMessageEvent = new MoneyChangeMessageEvent(this);
                    moneyChangeMessageEvent.setAccountId(one.getAccountId());
                    moneyChangeMessageEvent.setMoney(money);
                    moneyChangeMessageEvent.setMessageType(MessageType.FIVE);
                    moneyChangeMessageEvent.setMoneyChangeType(MoneyChangeType.SEVEN);
                    moneyChangeMessageEvent.setAccountVO(accountVO);
                    eventPublisher.publishEvent(moneyChangeMessageEvent);
                    one.setBalanceOf(balanceOf);
                    this.updateById(one);
                } catch (Exception e) {
                    log.error("e = {}",e);
                }
            }
        }

    }

}
