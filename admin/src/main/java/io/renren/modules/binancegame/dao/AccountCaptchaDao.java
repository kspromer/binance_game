package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.AccountCaptchaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统验证码
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-20 18:05:24
 */
@Mapper
public interface AccountCaptchaDao extends BaseMapper<AccountCaptchaEntity> {
	
}
