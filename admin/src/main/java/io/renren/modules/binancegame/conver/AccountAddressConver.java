package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.AccountAddressDTO;
import io.renren.modules.binancegame.entity.AccountAddressEntity;
import io.renren.modules.binancegame.vo.AccountAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface AccountAddressConver {

    AccountAddressConver MAPPER =  Mappers.getMapper(AccountAddressConver.class);

    AccountAddressEntity converDTO(AccountAddressDTO accountAddressDTO);

    List<AccountAddressEntity> converDTO(List<AccountAddressDTO> accountAddressDTOs);

    AccountAddressVO conver(AccountAddressEntity accountAddressEntities);

    List<AccountAddressVO> conver(List<AccountAddressEntity> accountAddressEntities);

}
