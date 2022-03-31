package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AppAccountWithdrawalSaveDto;
import io.renren.modules.binancegame.dto.AccountWithdrawalDTO;
import io.renren.modules.binancegame.service.AccountWithdrawalService;
import io.renren.modules.binancegame.vo.AccountWithdrawalVO;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 用户提现
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:00:03
 */
@RestController
@RequestMapping("app/binancegame/accountwithdrawal")
@Api(value="用户提现",tags="用户提现")
public class AppAccountWithdrawalController extends AbstractController {
    @Autowired
    private AccountWithdrawalService accountWithdrawalService;

    /**
     * 提交审核
     */
    @PostMapping("/save")
    @ApiOperation(value = "提交审核",notes = "提交审核")
    @ApiImplicitParams({
    })
    @Login
    public R save(@RequestBody AppAccountWithdrawalSaveDto accountWithdrawal){
        accountWithdrawal.setAccountId(getAccountId());
		accountWithdrawalService.save(accountWithdrawal);
        return R.ok();
    }

}
