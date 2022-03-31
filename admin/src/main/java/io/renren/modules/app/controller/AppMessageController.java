package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AppMessageListDto;
import io.renren.modules.binancegame.dto.MessageDTO;
import io.renren.modules.binancegame.entity.AppKlinesHistoryVO;
import io.renren.modules.binancegame.service.MessageService;
import io.renren.modules.binancegame.vo.MessageVO;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 消息
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 13:44:46
 */
@RestController
@RequestMapping("app/binancegame/message")
@Api(value="消息",tags="消息")
public class AppMessageController extends AbstractController {

    @Autowired
    private MessageService messageService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "消息列表",notes = "消息列表",response = MessageVO.class)
    @ApiImplicitParams({
    })
    @Login
    public R list(AppMessageListDto message){
        message.setAccountId(getAccountId());
        PageUtils page = messageService.listPage(message);
        return R.data(page);
    }
}
