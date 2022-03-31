package io.renren.modules.app.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.vo.AppKlinesCurrentIssueNoVO;
import io.renren.modules.binancegame.conver.KlinesConver;
import io.renren.modules.binancegame.dto.KlinesDTO;
import io.renren.modules.binancegame.entity.AppKlinesHistoryVO;
import io.renren.modules.binancegame.entity.KlinesEntity;
import io.renren.modules.binancegame.service.KlinesService;
import io.renren.modules.binancegame.vo.KlinesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-21 16:16:17
 */
@RestController
@RequestMapping("app/binancegame/klines")
@Api(value="k线接口",tags="k线接口")
public class AppKlinesController {
    @Autowired
    private KlinesService klinesService;

    /**
     * 历史记录
     */
    @GetMapping("/history")
    @ApiOperation(value = "历史记录",notes = "历史记录",response = AppKlinesHistoryVO.class)
    @ApiImplicitParams({
    })
    public R list(){
        List<KlinesEntity> list = klinesService.list(new QueryWrapper<KlinesEntity>().lambda()
                .le(KlinesEntity::getCloseTime, DateUtil.date())
                .orderByDesc(KlinesEntity::getIssueNo)
                .last("limit 60")
        );
        return R.data(KlinesConver.MAPPER.converAppKlinesHistoryVO(list));
    }

    /**
     * 获取当前期号
     */
    @GetMapping("/currentIssueNo")
    @ApiOperation(value = "获取当前期号",notes = "获取当前期号",response = AppKlinesCurrentIssueNoVO.class)
    @ApiImplicitParams({
    })
    public R currentIssueNo(){
        AppKlinesCurrentIssueNoVO vo = klinesService.currentIssueNo();
        return R.data(vo);
    }

}
