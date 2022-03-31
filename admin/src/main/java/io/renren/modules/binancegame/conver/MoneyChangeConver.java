package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.MoneyChangeDTO;
import io.renren.modules.binancegame.entity.MoneyChangeEntity;
import io.renren.modules.binancegame.vo.MoneyChangeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface MoneyChangeConver {

    MoneyChangeConver MAPPER =  Mappers.getMapper(MoneyChangeConver.class);

    MoneyChangeEntity converDTO(MoneyChangeDTO moneyChangeDTO);

    List<MoneyChangeEntity> converDTO(List<MoneyChangeDTO> moneyChangeDTOs);

    MoneyChangeVO conver(MoneyChangeEntity moneyChangeEntities);

    List<MoneyChangeVO> conver(List<MoneyChangeEntity> moneyChangeEntities);

}
