package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.KlinesDTO;
import io.renren.modules.binancegame.entity.AppKlinesHistoryVO;
import io.renren.modules.binancegame.entity.KlinesEntity;
import io.renren.modules.binancegame.vo.KlinesVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface KlinesConver {

    KlinesConver MAPPER =  Mappers.getMapper(KlinesConver.class);

    KlinesEntity converDTO(KlinesDTO klinesDTO);

    List<KlinesEntity> converDTO(List<KlinesDTO> klinesDTOs);

    KlinesVO conver(KlinesEntity klinesEntities);

    List<KlinesVO> conver(List<KlinesEntity> klinesEntities);

    AppKlinesHistoryVO converAppKlinesHistoryVO(KlinesEntity klinesEntities);

    List<AppKlinesHistoryVO> converAppKlinesHistoryVO(List<KlinesEntity> klinesEntities);

}
