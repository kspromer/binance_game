package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AppMessageListDto;
import io.renren.modules.binancegame.dto.MessageDTO;
import io.renren.modules.binancegame.vo.MessageVO;
import io.renren.modules.binancegame.entity.MessageEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 * 消息
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 13:44:46
 */
public interface MessageService extends IService<MessageEntity> {

    /**
     * 分页查询
     * @param message
     * @return
     */
    PageUtils queryPage(MessageDTO message);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    MessageVO getById(Long id);
    /**
     * 保存
     * @param message
     * @return
     */
    boolean save(MessageDTO message);
    /**
     * 根据id修改
     * @param message
     * @return
     */
    boolean updateById(MessageDTO message);
    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据id批量删除
     * @param ids
     * @return
     */
    @Override
    boolean removeByIds(Collection<? extends Serializable> ids);

    /**
     * 分页查询
     * @param message
     * @return
     */
    PageUtils listPage(AppMessageListDto message);
}

