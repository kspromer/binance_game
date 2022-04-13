package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.binancegame.dto.FranchiseConfigurationDTO;
import io.renren.modules.binancegame.vo.FranchiseConfigurationVO;
import io.renren.modules.binancegame.entity.FranchiseConfigurationEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 免赔配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-13 21:40:11
 */
public interface FranchiseConfigurationService extends IService<FranchiseConfigurationEntity> {

    /**
     * 分页查询
     * @param franchiseConfiguration
     * @return
     */
    PageUtils queryPage(FranchiseConfigurationDTO franchiseConfiguration);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    FranchiseConfigurationVO getById(Long id);
    /**
     * 保存
     * @param franchiseConfiguration
     * @return
     */
    boolean save(FranchiseConfigurationDTO franchiseConfiguration);
    /**
     * 根据id修改
     * @param franchiseConfiguration
     * @return
     */
    boolean updateById(FranchiseConfigurationDTO franchiseConfiguration);
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

