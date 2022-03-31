package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.binancegame.dto.BetConfigDTO;
import io.renren.modules.binancegame.entity.BetConfigEntity;
import io.renren.modules.binancegame.service.BetConfigService;
import io.renren.modules.binancegame.vo.BetConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 投注配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 11:26:37
 */
@RestController
@RequestMapping("app/binancegame/betconfig")
@Api(value="赔率配置",tags="赔率配置")
public class AppBetConfigController {
    @Autowired
    private BetConfigService betConfigService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "列表",notes = "列表")
    @ApiImplicitParams({
    })
    public R list(){
        List<BetConfigEntity> list = betConfigService.list();
        return R.data(list);
    }

}
