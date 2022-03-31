package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AppBetRecordBetDTO;
import io.renren.modules.app.dto.AppBetRecordListDTO;
import io.renren.modules.app.vo.AppBetRecordListVO;
import io.renren.modules.binancegame.dto.BetRecordDTO;
import io.renren.modules.binancegame.service.BetRecordService;
import io.renren.modules.binancegame.vo.BetRecordVO;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 15:01:17
 */
@RestController
@RequestMapping("app/binancegame/betrecord")
@Api(value="投注记录",tags="投注记录")
public class AppBetRecordController extends AbstractController {
    @Autowired
    private BetRecordService betRecordService;

    /**
     * 投注记录
     */
    @GetMapping("/list")
    @ApiOperation(value = "投注记录",notes = "投注记录",response = AppBetRecordListVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R list(AppBetRecordListDTO dto){
        dto.setAccountId(getAccountId());
        PageUtils page = betRecordService.listPage(dto);
        return R.data(page);
    }


    /**
     * 投注
     */
    @PostMapping("/bet")
    @ApiOperation(value = "投注",notes = "投注")
    @ApiImplicitParams({
    })
    @Login
    public R bet(@RequestBody AppBetRecordBetDTO dto){
        dto.setAccountId(getAccountId());
        betRecordService.bet(dto);
        return R.ok();
    }


}
