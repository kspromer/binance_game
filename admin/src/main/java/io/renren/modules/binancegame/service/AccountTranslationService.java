package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AppAccountTranslationTranslationDTO;
import io.renren.modules.binancegame.dto.AccountTranslationDTO;
import io.renren.modules.binancegame.vo.AccountTranslationVO;
import io.renren.modules.binancegame.entity.AccountTranslationEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 用户转账
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-11 15:00:13
 */
public interface AccountTranslationService extends IService<AccountTranslationEntity> {

    /**
     * 分页查询
     * @param accountTranslation
     * @return
     */
    PageUtils queryPage(AccountTranslationDTO accountTranslation);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountTranslationVO getById(Long id);
    /**
     * 保存
     * @param accountTranslation
     * @return
     */
    boolean save(AccountTranslationDTO accountTranslation);
    /**
     * 根据id修改
     * @param accountTranslation
     * @return
     */
    boolean updateById(AccountTranslationDTO accountTranslation);
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
     * 转账
     * @param dto
     */
    void translation(AppAccountTranslationTranslationDTO dto);
}

