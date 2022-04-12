package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import io.renren.modules.binancegame.dto.AccountAddMoneyDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AccountDTO;
import io.renren.modules.binancegame.vo.AccountVO;
import io.renren.modules.binancegame.service.AccountService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-21 15:04:16
 */
@RestController
@RequestMapping("binancegame/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:account:list")
    public R list(AccountDTO account){
        PageUtils page = accountService.queryPage(account);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:account:info")
    public R info(@PathVariable("id") Long id){
		AccountVO account = accountService.getById(id);

        return R.ok().put("account", account);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:account:save")
    public R save(@RequestBody AccountDTO account){
        accountService.save(account);

        return R.ok();
    }

    /**
     * 加分
     */
    @RequestMapping("/addMoney")
    @RequiresPermissions("binancegame:account:addMoney")
    public R addMoney(@RequestBody AccountAddMoneyDTO account){
        accountService.addMoney(account);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:account:update")
    public R update(@RequestBody AccountDTO account){
		accountService.updateById(account);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:account:delete")
    public R delete(@RequestBody Long[] ids){
		accountService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
