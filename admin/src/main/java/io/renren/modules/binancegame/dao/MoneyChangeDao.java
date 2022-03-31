package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.MoneyChangeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 金额变动
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:18:09
 */
@Mapper
public interface MoneyChangeDao extends BaseMapper<MoneyChangeEntity> {
	
}
