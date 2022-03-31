package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AccountCaptchaDTO;
import io.renren.modules.binancegame.vo.AccountCaptchaVO;
import io.renren.modules.binancegame.service.AccountCaptchaService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 系统验证码
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-20 18:05:24
 */
@RestController
@RequestMapping("binancegame/accountcaptcha")
public class AccountCaptchaController {
    @Autowired
    private AccountCaptchaService accountCaptchaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:accountcaptcha:list")
    public R list(AccountCaptchaDTO accountCaptcha){
        PageUtils page = accountCaptchaService.queryPage(accountCaptcha);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{uuid}")
    @RequiresPermissions("binancegame:accountcaptcha:info")
    public R info(@PathVariable("uuid") String uuid){
		AccountCaptchaVO accountCaptcha = accountCaptchaService.getById(uuid);

        return R.ok().put("accountCaptcha", accountCaptcha);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:accountcaptcha:save")
    public R save(@RequestBody AccountCaptchaDTO accountCaptcha){
		accountCaptchaService.save(accountCaptcha);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:accountcaptcha:update")
    public R update(@RequestBody AccountCaptchaDTO accountCaptcha){
		accountCaptchaService.updateById(accountCaptcha);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:accountcaptcha:delete")
    public R delete(@RequestBody String[] uuids){
		accountCaptchaService.removeByIds(Arrays.asList(uuids));

        return R.ok();
    }

}
