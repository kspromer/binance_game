package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.date.DateUtil;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AppAccountAddressSaveDto;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.AccountAddressDao;
import io.renren.modules.binancegame.entity.AccountAddressEntity;
import io.renren.modules.binancegame.dto.AccountAddressDTO;
import io.renren.modules.binancegame.vo.AccountAddressVO;
import io.renren.modules.binancegame.service.AccountAddressService;
import io.renren.modules.binancegame.conver.AccountAddressConver;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;


@Service("accountAddressService")
@BinanceGame
public class AccountAddressServiceImpl extends ServiceImpl<AccountAddressDao, AccountAddressEntity> implements AccountAddressService {

    @Override
    public PageUtils<AccountAddressVO> queryPage(AccountAddressDTO accountAddress) {
        IPage<AccountAddressEntity> page = baseMapper.selectPage(
                new Query<AccountAddressEntity>(accountAddress).getPage(),
                new QueryWrapper<AccountAddressEntity>()
        );

        return PageUtils.<AccountAddressVO>page(page).setList(AccountAddressConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AccountAddressVO getById(Long id) {
        return AccountAddressConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(AccountAddressDTO accountAddress) {
        AccountAddressEntity accountAddressEntity = AccountAddressConver.MAPPER.converDTO(accountAddress);
        return this.save(accountAddressEntity);
    }

    @Override
    public boolean updateById(AccountAddressDTO accountAddress) {
        AccountAddressEntity accountAddressEntity = AccountAddressConver.MAPPER.converDTO(accountAddress);
        return this.updateById(accountAddressEntity);
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
    public void save(AppAccountAddressSaveDto accountAddress) {
        AccountAddressDTO dto = new AccountAddressDTO();
        dto.setAccountId(accountAddress.getAccountId());
        dto.setAddress(accountAddress.getAddress());
        dto.setCoinType(accountAddress.getCoinType());
        dto.setLabel(accountAddress.getLabel());
        dto.setCreateTime(DateUtil.date());
        this.save(dto);
    }

}
