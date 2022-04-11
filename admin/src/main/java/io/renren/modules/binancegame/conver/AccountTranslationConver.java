package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.AccountTranslationDTO;
import io.renren.modules.binancegame.entity.AccountTranslationEntity;
import io.renren.modules.binancegame.vo.AccountTranslationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AccountTranslationConver {

    AccountTranslationConver MAPPER =  Mappers.getMapper(AccountTranslationConver.class);

    AccountTranslationEntity converDTO(AccountTranslationDTO accountTranslationDTO);

    List<AccountTranslationEntity> converDTO(List<AccountTranslationDTO> accountTranslationDTOs);

    AccountTranslationVO conver(AccountTranslationEntity accountTranslationEntities);

    List<AccountTranslationVO> conver(List<AccountTranslationEntity> accountTranslationEntities);

}
