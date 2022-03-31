package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.AccountCaptchaDTO;
import io.renren.modules.binancegame.entity.AccountCaptchaEntity;
import io.renren.modules.binancegame.vo.AccountCaptchaVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AccountCaptchaConver {

    AccountCaptchaConver MAPPER =  Mappers.getMapper(AccountCaptchaConver.class);

    AccountCaptchaEntity converDTO(AccountCaptchaDTO accountCaptchaDTO);

    List<AccountCaptchaEntity> converDTO(List<AccountCaptchaDTO> accountCaptchaDTOs);

    AccountCaptchaVO conver(AccountCaptchaEntity accountCaptchaEntities);

    List<AccountCaptchaVO> conver(List<AccountCaptchaEntity> accountCaptchaEntities);

}
