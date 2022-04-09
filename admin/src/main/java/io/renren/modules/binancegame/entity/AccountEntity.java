package io.renren.modules.binancegame.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-21 15:04:16
 */
@Data
@TableName("account")
@ApiModel("")
@Accessors(chain = true)
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Long id;
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
	 * 密码
	 */
	@ApiModelProperty(required=false,value="密码")
	private String password;
	/**
	 * 手机号
	 */
	@ApiModelProperty(required=false,value="手机号")
	private String phone;
	/**
	 * 加盐
	 */
	@ApiModelProperty(required=false,value="加盐")
	private String salt;
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
	/**
	 * 邀请人路径
	 */
	@ApiModelProperty(required=false,value="邀请人路径")
	private String upperPath;
}
