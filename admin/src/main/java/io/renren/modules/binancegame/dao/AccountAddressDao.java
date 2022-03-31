package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.AccountAddressEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户地址
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 15:39:21
 */
@Mapper
public interface AccountAddressDao extends BaseMapper<AccountAddressEntity> {
	
}
