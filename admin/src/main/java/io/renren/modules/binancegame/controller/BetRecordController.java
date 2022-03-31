package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.BetRecordDTO;
import io.renren.modules.binancegame.vo.BetRecordVO;
import io.renren.modules.binancegame.service.BetRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 15:01:17
 */
@RestController
@RequestMapping("binancegame/betrecord")
public class BetRecordController {
    @Autowired
    private BetRecordService betRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:betrecord:list")
    public R list(BetRecordDTO betRecord){
        PageUtils page = betRecordService.queryPage(betRecord);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:betrecord:info")
    public R info(@PathVariable("id") Long id){
		BetRecordVO betRecord = betRecordService.getById(id);

        return R.ok().put("betRecord", betRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:betrecord:save")
    public R save(@RequestBody BetRecordDTO betRecord){
		betRecordService.save(betRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:betrecord:update")
    public R update(@RequestBody BetRecordDTO betRecord){
		betRecordService.updateById(betRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:betrecord:delete")
    public R delete(@RequestBody Long[] ids){
		betRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
