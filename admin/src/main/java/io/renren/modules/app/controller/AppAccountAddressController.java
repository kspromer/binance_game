package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AppAccountAddressListDto;
import io.renren.modules.app.dto.AppAccountAddressSaveDto;
import io.renren.modules.binancegame.entity.AccountAddressEntity;
import io.renren.modules.binancegame.service.AccountAddressService;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 用户地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 15:39:21
 */
@RestController
@RequestMapping("app/binancegame/accountaddress")
@Api(value="用户地址",tags="用户地址")
public class AppAccountAddressController extends AbstractController {
    @Autowired
    private AccountAddressService accountAddressService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "列表",notes = "列表",response = AccountAddressEntity.class)
    @ApiImplicitParams({
    })
    @Login
    public R list(AppAccountAddressListDto accountAddress){
        List<AccountAddressEntity> list = accountAddressService.list(new QueryWrapper<AccountAddressEntity>().lambda()
                .eq(AccountAddressEntity::getAccountId,getAccountId())
                .eq(AccountAddressEntity::getCoinType,accountAddress.getCoinType())
        );
        return R.data(list);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存",notes = "保存")
    @ApiImplicitParams({
    })
    @Login
    public R save(@RequestBody AppAccountAddressSaveDto accountAddress){
        accountAddress.setAccountId(getAccountId());
		accountAddressService.save(accountAddress);
        return R.ok();
    }

}
