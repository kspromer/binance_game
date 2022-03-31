package io.renren.modules.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/21 14:50
 */
@Data
public class AppAccountVO {
    private static final long serialVersionUID = 1L;
    /**
     * 设备
     */
    @ApiModelProperty(required=false,value="设备")
    private String device;
    /**
     * 创建时间
     */
    @ApiModelProperty(required=false,value="创建时间")
    private Date createTime;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(required=false,value="最后登录时间")
    private Date lastLoginTime;
    /**
     * 用户名
     */
    @ApiModelProperty(required=false,value="用户名")
    private String username;
    /**
     * 手机号
     */
    @ApiModelProperty(required=false,value="手机号")
    private String phone;
    /**
     * 角色
     */
    @ApiModelProperty(required=false,value="角色")
    private String roleName;
    /**
     * 邀请码
     */
    @ApiModelProperty(required=false,value="邀请码")
    private String inviteCode;
    /**
     * 上级邀请人
     */
    @ApiModelProperty(required=false,value="上级邀请人")
    private Long upper;
    /**
     * 余额
     */
    @ApiModelProperty(required=false,value="余额")
    private BigDecimal money;
}
