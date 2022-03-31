package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.binancegame.dto.MoneyChangeDTO;
import io.renren.modules.binancegame.vo.MoneyChangeVO;
import io.renren.modules.binancegame.entity.MoneyChangeEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 金额变动
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:18:09
 */
public interface MoneyChangeService extends IService<MoneyChangeEntity> {

    /**
     * 分页查询
     * @param moneyChange
     * @return
     */
    PageUtils queryPage(MoneyChangeDTO moneyChange);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    MoneyChangeVO getById(Long id);
    /**
     * 保存
     * @param moneyChange
     * @return
     */
    boolean save(MoneyChangeDTO moneyChange);
    /**
     * 根据id修改
     * @param moneyChange
     * @return
     */
    boolean updateById(MoneyChangeDTO moneyChange);
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
}

