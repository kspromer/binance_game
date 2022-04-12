package io.renren.modules.binancegame.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
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
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service("accountRechargeAddressService")
@BinanceGame
public class AccountRechargeAddressServiceImpl extends ServiceImpl<AccountRechargeAddressDao, AccountRechargeAddressEntity> implements AccountRechargeAddressService {

    @Autowired
    private WalletClient walletClient;
    @Autowired
    private Web3j web3j;
    @Autowired
    private Web3jConfig web3jConfig;

    @Autowired
    private StaticGasProvider staticGasProvider;

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
        //获取生成的钱包数量
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
            if (ObjectUtil.isNotNull(one)) {
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
            }
        }
        return accountRechargeAddressGetAddressVOS;
    }

    /**
     * 充值USDT扫描
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rechargeTask() {
        //获取已经绑定地址的用户扫描
//        List<AccountRechargeAddressEntity> list = this.list(new QueryWrapper<AccountRechargeAddressEntity>().lambda()
//                .gt(AccountRechargeAddressEntity::getAccountId,0)
//        );
//
//        for (AccountRechargeAddressEntity one : list) {
//            ECKeyPair ecKeyPair = walletClient.createECKeyPair(one.getPrivateKey(), one.getWalletSerialNumber());
//            BscUsdt bscUsdt = BscUsdt.load(web3jConfig.getContractAddress(), web3j, Credentials.create(ecKeyPair), staticGasProvider);
//            try {
//                //当前的余额
//                BigInteger balanceOf = bscUsdt.balanceOf(one.getAddress()).send();
//                //如果余额相等直接跳出,说明没有充值
//                if (one.getBalanceOf().compareTo(balanceOf) == 0) {
//                    continue;
//                }
//            } catch (Exception e) {
//                log.error("e = {}",e);
//            }
//        }

    }

}
