package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.AccountWithdrawalDTO;
import io.renren.modules.binancegame.entity.AccountWithdrawalEntity;
import io.renren.modules.binancegame.vo.AccountWithdrawalVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AccountWithdrawalConver {

    AccountWithdrawalConver MAPPER =  Mappers.getMapper(AccountWithdrawalConver.class);

    AccountWithdrawalEntity converDTO(AccountWithdrawalDTO accountWithdrawalDTO);

    List<AccountWithdrawalEntity> converDTO(List<AccountWithdrawalDTO> accountWithdrawalDTOs);

    AccountWithdrawalVO conver(AccountWithdrawalEntity accountWithdrawalEntities);

    List<AccountWithdrawalVO> conver(List<AccountWithdrawalEntity> accountWithdrawalEntities);

}
