package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dto.AppBetRecordBetDTO;
import io.renren.modules.app.dto.AppBetRecordListDTO;
import io.renren.modules.binancegame.dto.BetRecordDTO;
import io.renren.modules.binancegame.vo.BetRecordVO;
import io.renren.modules.binancegame.entity.BetRecordEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 15:01:17
 */
public interface BetRecordService extends IService<BetRecordEntity> {

    /**
     * 分页查询
     * @param betRecord
     * @return
     */
    PageUtils queryPage(BetRecordDTO betRecord);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    BetRecordVO getById(Long id);
    /**
     * 保存
     * @param betRecord
     * @return
     */
    boolean save(BetRecordDTO betRecord);
    /**
     * 根据id修改
     * @param betRecord
     * @return
     */
    boolean updateById(BetRecordDTO betRecord);
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
     * 投注
     * @param dto
     */
    void bet(AppBetRecordBetDTO dto);
    /**
     * 投注记录
     * @param dto
     * @return
     */
    PageUtils listPage(AppBetRecordListDTO dto);
}

