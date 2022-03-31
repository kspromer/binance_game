package io.renren.modules.app.controller;

import cn.hutool.core.util.RadixUtil;
import io.renren.common.utils.R;
import io.renren.modules.app.dto.AppAccountLoginDTO;
import io.renren.modules.app.dto.AppAccountRegisterDTO;
import io.renren.modules.binancegame.service.AccountCaptchaService;
import io.renren.modules.binancegame.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-17 22:22:16
 */
@RestController
@RequestMapping("app/login")
@Api(value="登录接口",tags="登录接口")
public class AppAccountLoginController {

    @Autowired
    private AccountCaptchaService sysCaptchaService;
    @Autowired
    private AccountService accountService;

    /**
     * login
     */
    @PostMapping("login")
    @ApiOperation(value = "登录",notes = "登录",response = String.class)
    @ApiImplicitParams({
    })
    public R register(@Valid @RequestBody AppAccountLoginDTO dto) {
        return R.data(accountService.login(dto));
    }

}
