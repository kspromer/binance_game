package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.vo.AppAccountVO;
import io.renren.modules.binancegame.conver.AccountConver;
import io.renren.modules.binancegame.service.AccountService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-17 22:22:16
 */
@RestController
@RequestMapping("app/binancegame/account")
@Api(value="账号信息",tags="账号信息")
public class AppAccountController extends AbstractController {
    @Autowired
    private AccountService accountService;
    /**
     * 信息
     */
    @GetMapping("/info")
    @ApiOperation(value = "用户信息",notes = "用户信息",response = AppAccountVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R info(){
        return R.data(AccountConver.MAPPER.converAppAccountVO(accountService.getById(getAccountId())));
    }

}
