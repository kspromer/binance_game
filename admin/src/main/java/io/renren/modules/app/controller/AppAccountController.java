package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AccountInviterListDTO;
import io.renren.modules.app.vo.AccountShareInformationVO;
import io.renren.modules.app.vo.AccountTeamTotalInformationVO;
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

import java.util.ArrayList;


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


    /**
     * 分享信息
     */
    @GetMapping("/shareInformation")
    @ApiOperation(value = "分享信息",notes = "分享信息",response = AccountShareInformationVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R shareInformation(){
        return R.data(accountService.shareInformation(getAccountId()));
    }

    /**
     * 团队的总信息
     */
    @GetMapping("/teamTotalInformation")
    @ApiOperation(value = "团队信息",notes = "团队信息",response = AccountTeamTotalInformationVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R teamTotalInformation(){
        return R.data(accountService.teamTotalInformation(getAccountId()));
    }

    /**
     * 邀请人列表
     */
    @GetMapping("/inviterList")
    @ApiOperation(value = "邀请人列表",notes = "邀请人列表",response = AccountTeamTotalInformationVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R inviterList(AccountInviterListDTO accountInviterListDTO){
        accountInviterListDTO.setAccountId(getAccountId());
        return R.data(accountService.inviterList(accountInviterListDTO));
    }

}
