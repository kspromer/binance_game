package io.renren.modules.binancegame.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import io.renren.datasources.annotation.BinanceGame;
import io.renren.modules.app.dto.AppMessageListDto;
import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.event.MoneyChangeMessageEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.binancegame.dao.MessageDao;
import io.renren.modules.binancegame.entity.MessageEntity;
import io.renren.modules.binancegame.dto.MessageDTO;
import io.renren.modules.binancegame.vo.MessageVO;
import io.renren.modules.binancegame.service.MessageService;
import io.renren.modules.binancegame.conver.MessageConver;

import java.io.Serializable;
import java.util.Collection;


@Service("messageService")
@BinanceGame
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Override
    public PageUtils<MessageVO> queryPage(MessageDTO message) {
        IPage<MessageEntity> page = baseMapper.selectPage(
                new Query<MessageEntity>(message).getPage(),
                new QueryWrapper<MessageEntity>()
        );

        return PageUtils.<MessageVO>page(page).setList(MessageConver.MAPPER.conver(page.getRecords()));
    }
    @Override
    public MessageVO getById(Long id) {
        return MessageConver.MAPPER.conver(baseMapper.selectById(id));
    }

    @Override
    public boolean save(MessageDTO message) {
        MessageEntity messageEntity = MessageConver.MAPPER.converDTO(message);
        return this.save(messageEntity);
    }

    @Override
    public boolean updateById(MessageDTO message) {
        MessageEntity messageEntity = MessageConver.MAPPER.converDTO(message);
        return this.updateById(messageEntity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public PageUtils listPage(AppMessageListDto message) {
        IPage<MessageEntity> page = baseMapper.selectPage(
                new Query<MessageEntity>(message).getPage(),
                new QueryWrapper<MessageEntity>().lambda()
                        .in(MessageEntity::getAccountId, CollUtil.newArrayList(message.getAccountId(),-1))
                        .orderByDesc(MessageEntity::getId)
        );
        return PageUtils.<MessageVO>page(page).setList(MessageConver.MAPPER.conver(page.getRecords()));
    }

    /**
     * 刷新token监听器
     * @param event
     */
    @EventListener
    @Order(100)
    public void handlerMoneyChangeMessageEvent(MoneyChangeMessageEvent event) {
        Long accountId = event.getAccountId();
        MessageType messageType = event.getMessageType();
        if (ObjectUtil.isNotNull(messageType)) {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setAccountId(accountId);
            messageEntity.setTitle(messageType.getTitle());
            messageEntity.setDetail(messageType.getDetail());
            messageEntity.setType(messageType.getKey());
            messageEntity.setTypeStr(messageType.getValue());
            messageEntity.setCreateTime(DateUtil.date());
            this.save(messageEntity);
        }
    }

}
