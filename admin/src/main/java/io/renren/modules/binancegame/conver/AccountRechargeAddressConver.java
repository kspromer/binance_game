package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.AccountRechargeAddressDTO;
import io.renren.modules.binancegame.entity.AccountRechargeAddressEntity;
import io.renren.modules.binancegame.vo.AccountRechargeAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AccountRechargeAddressConver {

    AccountRechargeAddressConver MAPPER =  Mappers.getMapper(AccountRechargeAddressConver.class);

    AccountRechargeAddressEntity converDTO(AccountRechargeAddressDTO accountRechargeAddressDTO);

    List<AccountRechargeAddressEntity> converDTO(List<AccountRechargeAddressDTO> accountRechargeAddressDTOs);

    AccountRechargeAddressVO conver(AccountRechargeAddressEntity accountRechargeAddressEntities);

    List<AccountRechargeAddressVO> conver(List<AccountRechargeAddressEntity> accountRechargeAddressEntities);

}
