package io.renren.modules.binancegame.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统验证码
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-20 18:05:24
 */
@Data
@TableName("account_captcha")
@ApiModel("系统验证码")
@Accessors(chain = true)
public class AccountCaptchaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * uuid
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="uuid")
	private String uuid;
	/**
	 * 验证码
	 */
	@ApiModelProperty(required=false,value="验证码")
	private String code;
	/**
	 * 过期时间
	 */
	@ApiModelProperty(required=false,value="过期时间")
	private Date expireTime;

}
