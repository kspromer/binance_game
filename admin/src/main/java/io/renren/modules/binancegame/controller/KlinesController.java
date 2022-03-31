package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.KlinesDTO;
import io.renren.modules.binancegame.vo.KlinesVO;
import io.renren.modules.binancegame.service.KlinesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-21 16:16:17
 */
@RestController
@RequestMapping("binancegame/klines")
public class KlinesController {
    @Autowired
    private KlinesService klinesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:klines:list")
    public R list(KlinesDTO klines){
        PageUtils page = klinesService.queryPage(klines);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:klines:info")
    public R info(@PathVariable("id") Long id){
		KlinesVO klines = klinesService.getById(id);

        return R.ok().put("klines", klines);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:klines:save")
    public R save(@RequestBody KlinesDTO klines){
		klinesService.save(klines);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:klines:update")
    public R update(@RequestBody KlinesDTO klines){
		klinesService.updateById(klines);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:klines:delete")
    public R delete(@RequestBody Long[] ids){
		klinesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
