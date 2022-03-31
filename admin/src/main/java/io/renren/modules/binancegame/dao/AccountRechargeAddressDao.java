package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.AccountRechargeAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户充值地址
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 17:49:45
 */
@Mapper
public interface AccountRechargeAddressDao extends BaseMapper<AccountRechargeAddressEntity> {
	
}
