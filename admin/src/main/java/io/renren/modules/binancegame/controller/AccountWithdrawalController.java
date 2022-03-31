package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AccountWithdrawalDTO;
import io.renren.modules.binancegame.vo.AccountWithdrawalVO;
import io.renren.modules.binancegame.service.AccountWithdrawalService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户提现
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:00:03
 */
@RestController
@RequestMapping("binancegame/accountwithdrawal")
public class AccountWithdrawalController {
    @Autowired
    private AccountWithdrawalService accountWithdrawalService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:accountwithdrawal:list")
    public R list(AccountWithdrawalDTO accountWithdrawal){
        PageUtils page = accountWithdrawalService.queryPage(accountWithdrawal);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:accountwithdrawal:info")
    public R info(@PathVariable("id") Long id){
		AccountWithdrawalVO accountWithdrawal = accountWithdrawalService.getById(id);

        return R.ok().put("accountWithdrawal", accountWithdrawal);
    }

    /**
     * 保存
     */
    @RequestMapping("/audit")
    @RequiresPermissions("binancegame:accountwithdrawal:save")
    public R audit(@RequestBody AccountWithdrawalDTO accountWithdrawal){
		accountWithdrawalService.audit(accountWithdrawal);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:accountwithdrawal:save")
    public R save(@RequestBody AccountWithdrawalDTO accountWithdrawal){
        accountWithdrawalService.save(accountWithdrawal);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:accountwithdrawal:update")
    public R update(@RequestBody AccountWithdrawalDTO accountWithdrawal){
		accountWithdrawalService.updateById(accountWithdrawal);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:accountwithdrawal:delete")
    public R delete(@RequestBody Long[] ids){
		accountWithdrawalService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
