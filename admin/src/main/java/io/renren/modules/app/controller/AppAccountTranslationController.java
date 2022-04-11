package io.renren.modules.app.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.dto.AppAccountRegisterDTO;
import io.renren.modules.app.dto.AppAccountTranslationTranslationDTO;
import io.renren.modules.binancegame.dto.AccountTranslationDTO;
import io.renren.modules.binancegame.service.AccountTranslationService;
import io.renren.modules.binancegame.vo.AccountTranslationVO;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


/**
 * 用户转账
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-11 15:00:13
 */
@RestController
@RequestMapping("app/binancegame/accounttranslation")
public class AppAccountTranslationController extends AbstractController {

    @Autowired
    private AccountTranslationService accountTranslationService;

    /**
     * 转账
     */
    @PostMapping("translation")
    @ApiOperation(value = "转账",notes = "转账")
    @ApiImplicitParams({
    })
    @Login
    public R translation(@Valid @RequestBody AppAccountTranslationTranslationDTO dto) {
        dto.setAccountId(getAccountId());
        accountTranslationService.translation(dto);
        return R.ok();
    }
}
