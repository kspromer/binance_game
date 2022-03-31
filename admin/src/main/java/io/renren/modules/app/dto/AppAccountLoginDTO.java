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
public class AppAccountLoginDTO {
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
