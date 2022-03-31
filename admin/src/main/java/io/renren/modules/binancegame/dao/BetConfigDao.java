package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.BetConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 投注配置
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 11:26:37
 */
@Mapper
public interface BetConfigDao extends BaseMapper<BetConfigEntity> {
	
}
