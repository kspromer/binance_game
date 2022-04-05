package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.AgentCommissionDTO;
import io.renren.modules.binancegame.vo.AgentCommissionVO;
import io.renren.modules.binancegame.service.AgentCommissionService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 分佣配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-04 18:15:07
 */
@RestController
@RequestMapping("binancegame/agentcommission")
public class AgentCommissionController {
    @Autowired
    private AgentCommissionService agentCommissionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:agentcommission:list")
    public R list(AgentCommissionDTO agentCommission){
        PageUtils page = agentCommissionService.queryPage(agentCommission);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:agentcommission:info")
    public R info(@PathVariable("id") Long id){
		AgentCommissionVO agentCommission = agentCommissionService.getById(id);

        return R.ok().put("agentCommission", agentCommission);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:agentcommission:save")
    public R save(@RequestBody AgentCommissionDTO agentCommission){
		agentCommissionService.save(agentCommission);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:agentcommission:update")
    public R update(@RequestBody AgentCommissionDTO agentCommission){
		agentCommissionService.updateById(agentCommission);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:agentcommission:delete")
    public R delete(@RequestBody Long[] ids){
		agentCommissionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
