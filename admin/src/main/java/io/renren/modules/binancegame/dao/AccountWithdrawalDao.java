package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.AccountWithdrawalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户提现
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:00:03
 */
@Mapper
public interface AccountWithdrawalDao extends BaseMapper<AccountWithdrawalEntity> {
	
}
