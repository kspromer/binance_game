package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.AccountTranslationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户转账
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-11 15:00:13
 */
@Mapper
public interface AccountTranslationDao extends BaseMapper<AccountTranslationEntity> {
	
}
