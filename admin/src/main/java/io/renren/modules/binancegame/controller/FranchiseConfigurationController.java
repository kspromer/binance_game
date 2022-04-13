package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.FranchiseConfigurationDTO;
import io.renren.modules.binancegame.vo.FranchiseConfigurationVO;
import io.renren.modules.binancegame.service.FranchiseConfigurationService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 免赔配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-13 21:40:11
 */
@RestController
@RequestMapping("binancegame/franchiseconfiguration")
public class FranchiseConfigurationController {
    @Autowired
    private FranchiseConfigurationService franchiseConfigurationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:franchiseconfiguration:list")
    public R list(FranchiseConfigurationDTO franchiseConfiguration){
        PageUtils page = franchiseConfigurationService.queryPage(franchiseConfiguration);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:franchiseconfiguration:info")
    public R info(@PathVariable("id") Long id){
		FranchiseConfigurationVO franchiseConfiguration = franchiseConfigurationService.getById(id);

        return R.ok().put("franchiseConfiguration", franchiseConfiguration);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:franchiseconfiguration:save")
    public R save(@RequestBody FranchiseConfigurationDTO franchiseConfiguration){
		franchiseConfigurationService.save(franchiseConfiguration);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:franchiseconfiguration:update")
    public R update(@RequestBody FranchiseConfigurationDTO franchiseConfiguration){
		franchiseConfigurationService.updateById(franchiseConfiguration);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:franchiseconfiguration:delete")
    public R delete(@RequestBody Long[] ids){
		franchiseConfigurationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
