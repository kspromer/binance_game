package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.AgentCommissionDTO;
import io.renren.modules.binancegame.entity.AgentCommissionEntity;
import io.renren.modules.binancegame.vo.AgentCommissionVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AgentCommissionConver {

    AgentCommissionConver MAPPER =  Mappers.getMapper(AgentCommissionConver.class);

    AgentCommissionEntity converDTO(AgentCommissionDTO agentCommissionDTO);

    List<AgentCommissionEntity> converDTO(List<AgentCommissionDTO> agentCommissionDTOs);

    AgentCommissionVO conver(AgentCommissionEntity agentCommissionEntities);

    List<AgentCommissionVO> conver(List<AgentCommissionEntity> agentCommissionEntities);

}
