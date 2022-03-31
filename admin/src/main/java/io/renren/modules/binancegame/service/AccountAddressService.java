package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AppAccountAddressSaveDto;
import io.renren.modules.binancegame.dto.AccountAddressDTO;
import io.renren.modules.binancegame.vo.AccountAddressVO;
import io.renren.modules.binancegame.entity.AccountAddressEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 用户地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 15:39:21
 */
public interface AccountAddressService extends IService<AccountAddressEntity> {

    /**
     * 分页查询
     * @param accountAddress
     * @return
     */
    PageUtils queryPage(AccountAddressDTO accountAddress);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountAddressVO getById(Long id);
    /**
     * 保存
     * @param accountAddress
     * @return
     */
    boolean save(AccountAddressDTO accountAddress);
    /**
     * 根据id修改
     * @param accountAddress
     * @return
     */
    boolean updateById(AccountAddressDTO accountAddress);
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
     * 用户地址保存
     * @param accountAddress
     */
    void save(AppAccountAddressSaveDto accountAddress);
}

