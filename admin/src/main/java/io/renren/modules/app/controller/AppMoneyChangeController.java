package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AccountInviterListDTO;
import io.renren.modules.app.dto.AccountRebateRecordDTO;
import io.renren.modules.app.vo.AccountTeamTotalInformationVO;
import io.renren.modules.binancegame.dto.MoneyChangeDTO;
import io.renren.modules.binancegame.service.MoneyChangeService;
import io.renren.modules.binancegame.vo.MoneyChangeVO;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 金额变动
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:18:09
 */
@RestController
@RequestMapping("app/binancegame/moneychange")
@Api(value="金额变动",tags="金额变动")
public class AppMoneyChangeController extends AbstractController {
    @Autowired
    private MoneyChangeService moneyChangeService;

    /**
     * 返利记录
     */
    @GetMapping("/rebateRecord")
    @ApiOperation(value = "返利记录",notes = "返利记录",response = AccountTeamTotalInformationVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R rebateRecord(AccountRebateRecordDTO accountRebateRecordDTO){
        accountRebateRecordDTO.setAccountId(getAccountId());
        return R.data(moneyChangeService.rebateRecord(accountRebateRecordDTO));
    }
}
