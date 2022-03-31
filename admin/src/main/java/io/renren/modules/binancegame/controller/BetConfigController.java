package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.BetConfigDTO;
import io.renren.modules.binancegame.vo.BetConfigVO;
import io.renren.modules.binancegame.service.BetConfigService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 投注配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 11:26:37
 */
@RestController
@RequestMapping("binancegame/betconfig")
public class BetConfigController {
    @Autowired
    private BetConfigService betConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:betconfig:list")
    public R list(BetConfigDTO betConfig){
        PageUtils page = betConfigService.queryPage(betConfig);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:betconfig:info")
    public R info(@PathVariable("id") Integer id){
		BetConfigVO betConfig = betConfigService.getById(id);

        return R.ok().put("betConfig", betConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:betconfig:save")
    public R save(@RequestBody BetConfigDTO betConfig){
		betConfigService.save(betConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:betconfig:update")
    public R update(@RequestBody BetConfigDTO betConfig){
		betConfigService.updateById(betConfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:betconfig:delete")
    public R delete(@RequestBody Integer[] ids){
		betConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
