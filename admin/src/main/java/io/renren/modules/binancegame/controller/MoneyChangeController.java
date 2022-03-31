package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.MoneyChangeDTO;
import io.renren.modules.binancegame.vo.MoneyChangeVO;
import io.renren.modules.binancegame.service.MoneyChangeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 金额变动
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:18:09
 */
@RestController
@RequestMapping("binancegame/moneychange")
public class MoneyChangeController {
    @Autowired
    private MoneyChangeService moneyChangeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:moneychange:list")
    public R list(MoneyChangeDTO moneyChange){
        PageUtils page = moneyChangeService.queryPage(moneyChange);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:moneychange:info")
    public R info(@PathVariable("id") Long id){
		MoneyChangeVO moneyChange = moneyChangeService.getById(id);

        return R.ok().put("moneyChange", moneyChange);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:moneychange:save")
    public R save(@RequestBody MoneyChangeDTO moneyChange){
		moneyChangeService.save(moneyChange);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:moneychange:update")
    public R update(@RequestBody MoneyChangeDTO moneyChange){
		moneyChangeService.updateById(moneyChange);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:moneychange:delete")
    public R delete(@RequestBody Long[] ids){
		moneyChangeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
