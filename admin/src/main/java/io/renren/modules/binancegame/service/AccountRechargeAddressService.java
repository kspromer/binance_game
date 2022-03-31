package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AccountRechargeAddressGetAddressDto;
import io.renren.modules.app.vo.AccountRechargeAddressGetAddressVO;
import io.renren.modules.binancegame.dto.AccountRechargeAddressDTO;
import io.renren.modules.binancegame.vo.AccountRechargeAddressVO;
import io.renren.modules.binancegame.entity.AccountRechargeAddressEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * 用户充值地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 17:49:45
 */
public interface AccountRechargeAddressService extends IService<AccountRechargeAddressEntity> {

    /**
     * 分页查询
     * @param accountRechargeAddress
     * @return
     */
    PageUtils queryPage(AccountRechargeAddressDTO accountRechargeAddress);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountRechargeAddressVO getById(Long id);
    /**
     * 保存
     * @param accountRechargeAddress
     * @return
     */
    boolean save(AccountRechargeAddressDTO accountRechargeAddress);
    /**
     * 根据id修改
     * @param accountRechargeAddress
     * @return
     */
    boolean updateById(AccountRechargeAddressDTO accountRechargeAddress);
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据id批量删除
     * @param ids
     * @return
     */
    @Override
    boolean removeByIds(Collection<? extends Serializable> ids);

    /**
     * 获取地址
     * @param dto
     * @return
     */
    List<AccountRechargeAddressGetAddressVO> getAddress(AccountRechargeAddressGetAddressDto dto);

    /**
     * 充值USDT扫描
     */
    void rechargeTask();
}

