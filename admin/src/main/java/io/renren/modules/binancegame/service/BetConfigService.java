package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.binancegame.dto.BetConfigDTO;
import io.renren.modules.binancegame.vo.BetConfigVO;
import io.renren.modules.binancegame.entity.BetConfigEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 投注配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 11:26:37
 */
public interface BetConfigService extends IService<BetConfigEntity> {

    /**
     * 分页查询
     * @param betConfig
     * @return
     */
    PageUtils queryPage(BetConfigDTO betConfig);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    BetConfigVO getById(Integer id);
    /**
     * 保存
     * @param betConfig
     * @return
     */
    boolean save(BetConfigDTO betConfig);
    /**
     * 根据id修改
     * @param betConfig
     * @return
     */
    boolean updateById(BetConfigDTO betConfig);
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

