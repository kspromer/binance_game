package io.renren.modules.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/20 17:22
 */
@Data
public class AppAccountRegisterDTO {
    /**
     * 邀请码
     */
    @ApiModelProperty(required=false,value="邀请码")
    private String inviteCode;
    /**
     * uuid
     */
    @NotBlank(message = "UUID cannot be empty")
    private String uuid;
    /**
     * 验证码
     */
    @ApiModelProperty(required=false,value="验证码")
    @NotBlank(message = "verification code cannot be empty")
    private String code;
    /**
     * 用户名
     */
    @ApiModelProperty(required=false,value="用户名")
    @NotBlank(message = "username cannot be empty")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(required=false,value="密码")
    @NotBlank(message = "password cannot be empty")
    private String password;
}
