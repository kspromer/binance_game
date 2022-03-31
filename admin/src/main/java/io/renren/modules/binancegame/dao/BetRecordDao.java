package io.renren.modules.binancegame.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.modules.app.dto.AppBetRecordListDTO;
import io.renren.modules.app.vo.AppBetRecordListIssueVO;
import io.renren.modules.app.vo.AppBetRecordListVO;
import io.renren.modules.binancegame.entity.BetRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 15:01:17
 */
@Mapper
public interface BetRecordDao extends BaseMapper<BetRecordEntity> {
    /**
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    IPage<AppBetRecordListIssueVO> listPage(Page<BetRecordEntity> page, @Param("dto") AppBetRecordListDTO dto);
}
