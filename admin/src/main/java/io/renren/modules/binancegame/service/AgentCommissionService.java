package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.binancegame.dto.AgentCommissionDTO;
import io.renren.modules.binancegame.vo.AgentCommissionVO;
import io.renren.modules.binancegame.entity.AgentCommissionEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 分佣配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-04 18:15:07
 */
public interface AgentCommissionService extends IService<AgentCommissionEntity> {

    /**
     * 分页查询
     * @param agentCommission
     * @return
     */
    PageUtils queryPage(AgentCommissionDTO agentCommission);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    AgentCommissionVO getById(Long id);
    /**
     * 保存
     * @param agentCommission
     * @return
     */
    boolean save(AgentCommissionDTO agentCommission);
    /**
     * 根据id修改
     * @param agentCommission
     * @return
     */
    boolean updateById(AgentCommissionDTO agentCommission);
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

