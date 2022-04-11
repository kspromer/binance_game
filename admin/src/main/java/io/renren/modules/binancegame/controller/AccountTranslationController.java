package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AccountTranslationDTO;
import io.renren.modules.binancegame.vo.AccountTranslationVO;
import io.renren.modules.binancegame.service.AccountTranslationService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户转账
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-11 15:00:13
 */
@RestController
@RequestMapping("binancegame/accounttranslation")
public class AccountTranslationController {
    @Autowired
    private AccountTranslationService accountTranslationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:accounttranslation:list")
    public R list(AccountTranslationDTO accountTranslation){
        PageUtils page = accountTranslationService.queryPage(accountTranslation);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:accounttranslation:info")
    public R info(@PathVariable("id") Long id){
		AccountTranslationVO accountTranslation = accountTranslationService.getById(id);

        return R.ok().put("accountTranslation", accountTranslation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:accounttranslation:save")
    public R save(@RequestBody AccountTranslationDTO accountTranslation){
		accountTranslationService.save(accountTranslation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:accounttranslation:update")
    public R update(@RequestBody AccountTranslationDTO accountTranslation){
		accountTranslationService.updateById(accountTranslation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:accounttranslation:delete")
    public R delete(@RequestBody Long[] ids){
		accountTranslationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
