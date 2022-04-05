package io.renren.modules.binancegame.service.impl;

import io.renren.datasources.annotation.BinanceGame;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.AgentCommissionDao;
import io.renren.modules.binancegame.entity.AgentCommissionEntity;
import io.renren.modules.binancegame.dto.AgentCommissionDTO;
import io.renren.modules.binancegame.vo.AgentCommissionVO;
import io.renren.modules.binancegame.service.AgentCommissionService;
import io.renren.modules.binancegame.conver.AgentCommissionConver;

import java.io.Serializable;
import java.util.Collection;


@Service("agentCommissionService")
@BinanceGame
public class AgentCommissionServiceImpl extends ServiceImpl<AgentCommissionDao, AgentCommissionEntity> implements AgentCommissionService {

    @Override
    public PageUtils<AgentCommissionVO> queryPage(AgentCommissionDTO agentCommission) {
        IPage<AgentCommissionEntity> page = baseMapper.selectPage(
                new Query<AgentCommissionEntity>(agentCommission).getPage(),
                new QueryWrapper<AgentCommissionEntity>()
        );

        return PageUtils.<AgentCommissionVO>page(page).setList(AgentCommissionConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public AgentCommissionVO getById(Long id) {
        return AgentCommissionConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(AgentCommissionDTO agentCommission) {
        AgentCommissionEntity agentCommissionEntity = AgentCommissionConver.MAPPER.converDTO(agentCommission);
        return this.save(agentCommissionEntity);
    }

    @Override
    public boolean updateById(AgentCommissionDTO agentCommission) {
        AgentCommissionEntity agentCommissionEntity = AgentCommissionConver.MAPPER.converDTO(agentCommission);
        return this.updateById(agentCommissionEntity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }

}
