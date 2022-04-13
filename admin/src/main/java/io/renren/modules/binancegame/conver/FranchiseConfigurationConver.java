package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.FranchiseConfigurationDTO;
import io.renren.modules.binancegame.entity.FranchiseConfigurationEntity;
import io.renren.modules.binancegame.vo.FranchiseConfigurationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface FranchiseConfigurationConver {

    FranchiseConfigurationConver MAPPER =  Mappers.getMapper(FranchiseConfigurationConver.class);

    FranchiseConfigurationEntity converDTO(FranchiseConfigurationDTO franchiseConfigurationDTO);

    List<FranchiseConfigurationEntity> converDTO(List<FranchiseConfigurationDTO> franchiseConfigurationDTOs);

    FranchiseConfigurationVO conver(FranchiseConfigurationEntity franchiseConfigurationEntities);

    List<FranchiseConfigurationVO> conver(List<FranchiseConfigurationEntity> franchiseConfigurationEntities);

}
