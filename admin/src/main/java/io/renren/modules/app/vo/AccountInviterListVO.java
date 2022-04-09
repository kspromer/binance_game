package io.renren.modules.app.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/9 15:49
 */
@Data
@Accessors(chain = true)
public class AccountInviterListVO {
    private static final long serialVersionUID = 1L;
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
     * 邀请码
     */
    @ApiModelProperty(required=false,value="邀请码")
    private String inviteCode;
}
