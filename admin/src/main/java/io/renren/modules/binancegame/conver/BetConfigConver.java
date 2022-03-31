package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.BetConfigDTO;
import io.renren.modules.binancegame.entity.BetConfigEntity;
import io.renren.modules.binancegame.vo.BetConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface BetConfigConver {

    BetConfigConver MAPPER =  Mappers.getMapper(BetConfigConver.class);

    BetConfigEntity converDTO(BetConfigDTO betConfigDTO);

    List<BetConfigEntity> converDTO(List<BetConfigDTO> betConfigDTOs);

    BetConfigVO conver(BetConfigEntity betConfigEntities);

    List<BetConfigVO> conver(List<BetConfigEntity> betConfigEntities);

}
