package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.BetRecordDTO;
import io.renren.modules.binancegame.entity.BetRecordEntity;
import io.renren.modules.binancegame.vo.BetRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface BetRecordConver {

    BetRecordConver MAPPER =  Mappers.getMapper(BetRecordConver.class);

    BetRecordEntity converDTO(BetRecordDTO betRecordDTO);

    List<BetRecordEntity> converDTO(List<BetRecordDTO> betRecordDTOs);

    BetRecordVO conver(BetRecordEntity betRecordEntities);

    List<BetRecordVO> conver(List<BetRecordEntity> betRecordEntities);

}
