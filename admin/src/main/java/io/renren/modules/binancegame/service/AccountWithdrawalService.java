package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AppAccountWithdrawalSaveDto;
import io.renren.modules.binancegame.dto.AccountWithdrawalDTO;
import io.renren.modules.binancegame.vo.AccountWithdrawalVO;
import io.renren.modules.binancegame.entity.AccountWithdrawalEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 用户提现
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:00:03
 */
public interface AccountWithdrawalService extends IService<AccountWithdrawalEntity> {

    /**
     * 分页查询
     * @param accountWithdrawal
     * @return
     */
    PageUtils queryPage(AccountWithdrawalDTO accountWithdrawal);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountWithdrawalVO getById(Long id);
    /**
     * 保存
     * @param accountWithdrawal
     * @return
     */
    boolean save(AccountWithdrawalDTO accountWithdrawal);
    /**
     * 根据id修改
     * @param accountWithdrawal
     * @return
     */
    boolean updateById(AccountWithdrawalDTO accountWithdrawal);
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
     * 提现
     * @param accountWithdrawal
     */
    void save(AppAccountWithdrawalSaveDto accountWithdrawal);

    /**
     * 审核
     * @param accountWithdrawal
     */
    void audit(AccountWithdrawalDTO accountWithdrawal);
}

