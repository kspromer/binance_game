package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AccountRechargeAddressGetAddressDto;
import io.renren.modules.binancegame.service.AccountRechargeAddressService;
import io.renren.modules.binancegame.vo.AccountRechargeAddressVO;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户充值地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 17:49:45
 */
@RestController
@RequestMapping("app/binancegame/accountrechargeaddress")
@Api(value="用户充值地址",tags="用户充值地址")
public class AppAccountRechargeAddressController extends AbstractController {
    @Autowired
    private AccountRechargeAddressService accountRechargeAddressService;

    /**
     * 获取充值
     */
    @GetMapping("/getAddress")
    @ApiOperation(value = "获取充值",notes = "获取充值",response = AccountRechargeAddressVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R getAddress(AccountRechargeAddressGetAddressDto dto){
        dto.setAccountId(getAccountId());
        return R.data(accountRechargeAddressService.getAddress(dto));
    }


}
