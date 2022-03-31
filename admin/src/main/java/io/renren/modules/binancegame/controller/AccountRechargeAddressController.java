package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AccountRechargeAddressDTO;
import io.renren.modules.binancegame.vo.AccountRechargeAddressVO;
import io.renren.modules.binancegame.service.AccountRechargeAddressService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户充值地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 17:49:45
 */
@RestController
@RequestMapping("binancegame/accountrechargeaddress")
public class AccountRechargeAddressController {
    @Autowired
    private AccountRechargeAddressService accountRechargeAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:accountrechargeaddress:list")
    public R list(AccountRechargeAddressDTO accountRechargeAddress){
        PageUtils page = accountRechargeAddressService.queryPage(accountRechargeAddress);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:accountrechargeaddress:info")
    public R info(@PathVariable("id") Long id){
		AccountRechargeAddressVO accountRechargeAddress = accountRechargeAddressService.getById(id);

        return R.ok().put("accountRechargeAddress", accountRechargeAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:accountrechargeaddress:save")
    public R save(@RequestBody AccountRechargeAddressDTO accountRechargeAddress){
		accountRechargeAddressService.save(accountRechargeAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:accountrechargeaddress:update")
    public R update(@RequestBody AccountRechargeAddressDTO accountRechargeAddress){
		accountRechargeAddressService.updateById(accountRechargeAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:accountrechargeaddress:delete")
    public R delete(@RequestBody Long[] ids){
		accountRechargeAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
