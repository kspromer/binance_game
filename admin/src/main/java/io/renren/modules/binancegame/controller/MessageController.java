package io.renren.modules.binancegame.controller;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.binancegame.dto.MessageDTO;
import io.renren.modules.binancegame.vo.MessageVO;
import io.renren.modules.binancegame.service.MessageService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 消息
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 13:44:46
 */
@RestController
@RequestMapping("binancegame/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("binancegame:message:list")
    public R list(MessageDTO message){
        PageUtils page = messageService.queryPage(message);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("binancegame:message:info")
    public R info(@PathVariable("id") Long id){
		MessageVO message = messageService.getById(id);

        return R.ok().put("message", message);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("binancegame:message:save")
    public R save(@RequestBody MessageDTO message){
		messageService.save(message);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("binancegame:message:update")
    public R update(@RequestBody MessageDTO message){
		messageService.updateById(message);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("binancegame:message:delete")
    public R delete(@RequestBody Long[] ids){
		messageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
