package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AccountAddressDTO;
import io.renren.modules.binancegame.vo.AccountAddressVO;
import io.renren.modules.binancegame.service.AccountAddressService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 15:39:21
 */
@RestController
@RequestMapping("binancegame/accountaddress")
public class AccountAddressController {
    @Autowired
    private AccountAddressService accountAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:accountaddress:list")
    public R list(AccountAddressDTO accountAddress){
        PageUtils page = accountAddressService.queryPage(accountAddress);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:accountaddress:info")
    public R info(@PathVariable("id") Long id){
		AccountAddressVO accountAddress = accountAddressService.getById(id);

        return R.ok().put("accountAddress", accountAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:accountaddress:save")
    public R save(@RequestBody AccountAddressDTO accountAddress){
		accountAddressService.save(accountAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:accountaddress:update")
    public R update(@RequestBody AccountAddressDTO accountAddress){
		accountAddressService.updateById(accountAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:accountaddress:delete")
    public R delete(@RequestBody Long[] ids){
		accountAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
