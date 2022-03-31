package io.renren.modules.binancegame.dao;

import io.renren.modules.binancegame.entity.MessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息
 * 
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 13:44:46
 */
@Mapper
public interface MessageDao extends BaseMapper<MessageEntity> {
	
}
