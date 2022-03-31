package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AppAccountLoginDTO;
import io.renren.modules.app.dto.AppAccountRegisterDTO;
import io.renren.modules.binancegame.dto.AccountDTO;
import io.renren.modules.binancegame.vo.AccountVO;
import io.renren.modules.binancegame.entity.AccountEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-20 17:28:20
 */
public interface AccountService extends IService<AccountEntity> {

    /**
     * 分页查询
     * @param account
     * @return
     */
    PageUtils queryPage(AccountDTO account);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountVO getById(Long id);
    /**
     * 保存
     * @param account
     * @return
     */
    boolean save(AccountDTO account);
    /**
     * 根据id修改
     * @param account
     * @return
     */
    boolean updateById(AccountDTO account);
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
     * 注册
     * @param dto
     */
    void register(AppAccountRegisterDTO dto);
    /**
     * 登录
     * @param dto
     * @return
     */
    String login(AppAccountLoginDTO dto);
}

