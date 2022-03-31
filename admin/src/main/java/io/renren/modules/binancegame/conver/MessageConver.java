package io.renren.modules.binancegame.conver;

import io.renren.modules.binancegame.dto.MessageDTO;
import io.renren.modules.binancegame.entity.MessageEntity;
import io.renren.modules.binancegame.vo.MessageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-02-07 12:36
 */
@Mapper
public interface MessageConver {

    MessageConver MAPPER =  Mappers.getMapper(MessageConver.class);

    MessageEntity converDTO(MessageDTO messageDTO);

    List<MessageEntity> converDTO(List<MessageDTO> messageDTOs);

    MessageVO conver(MessageEntity messageEntities);

    List<MessageVO> conver(List<MessageEntity> messageEntities);

}
