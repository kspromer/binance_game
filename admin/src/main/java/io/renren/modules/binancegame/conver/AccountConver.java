package io.renren.modules.binancegame.conver;

import io.renren.modules.app.vo.AccountInviterListVO;
import io.renren.modules.app.vo.AppAccountVO;
import io.renren.modules.binancegame.dto.AccountDTO;
import io.renren.modules.binancegame.entity.AccountEntity;
import io.renren.modules.binancegame.vo.AccountVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AccountConver {

    AccountConver MAPPER =  Mappers.getMapper(AccountConver.class);

    AccountEntity converDTO(AccountDTO accountDTO);

    List<AccountEntity> converDTO(List<AccountDTO> accountDTOs);

    AccountVO conver(AccountEntity accountEntities);

    List<AccountVO> conver(List<AccountEntity> accountEntities);

    AppAccountVO converAppAccountVO(AccountVO accountVO);

    List<AppAccountVO> converAppAccountVO(List<AccountVO> accountVOS);

    AccountInviterListVO converInviterList(AccountEntity accountEntities);

    List<AccountInviterListVO> converInviterList(List<AccountEntity> accountEntities);
}
