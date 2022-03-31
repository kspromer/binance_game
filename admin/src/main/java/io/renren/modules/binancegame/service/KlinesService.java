package io.renren.modules.binancegame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.vo.AppKlinesCurrentIssueNoVO;
import io.renren.modules.binancegame.dto.KlinesDTO;
import io.renren.modules.binancegame.vo.KlinesVO;
import io.renren.modules.binancegame.entity.KlinesEntity;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-21 16:16:17
 */
public interface KlinesService extends IService<KlinesEntity> {

    /**
     * 分页查询
     * @param klines
     * @return
     */
    PageUtils queryPage(KlinesDTO klines);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    KlinesVO getById(Long id);
    /**
     * 保存
     * @param klines
     * @return
     */
    boolean save(KlinesDTO klines);
    /**
     * 根据id修改
     * @param klines
     * @return
     */
    boolean updateById(KlinesDTO klines);
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
     * 获取当前期号
     * @return
     */
    AppKlinesCurrentIssueNoVO currentIssueNo();
    /**
     * 结算
     */
    void settlement();

    /**
     * 刷新当前期号的缓存
     */
    void currentIssueNoCacheRefresh();

}

