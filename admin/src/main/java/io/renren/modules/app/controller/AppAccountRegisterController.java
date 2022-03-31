package io.renren.modules.app.controller;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RadixUtil;
import cn.hutool.core.util.RandomUtil;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.dto.AppAccountRegisterDTO;
import io.renren.modules.binancegame.dto.AccountDTO;
import io.renren.modules.binancegame.service.AccountCaptchaService;
import io.renren.modules.binancegame.service.AccountService;
import io.renren.modules.binancegame.vo.AccountVO;
import io.renren.modules.sys.service.SysCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-17 22:22:16
 */
@RestController
@RequestMapping("app/register")
@Api(value="注册接口",tags="注册接口")
public class AppAccountRegisterController {


    @Autowired
    private AccountCaptchaService sysCaptchaService;
    @Autowired
    private AccountService accountService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    @ApiOperation(value = "获取验证码",notes = "获取验证码")
    @ApiImplicitParams({
    })
    public void captcha(HttpServletResponse response, String uuid)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 注册
     */
    @PostMapping("register")
    @ApiOperation(value = "注册",notes = "注册")
    @ApiImplicitParams({
    })
    public R register(@Valid @RequestBody AppAccountRegisterDTO dto) {
        accountService.register(dto);
        return R.ok();
    }

}
