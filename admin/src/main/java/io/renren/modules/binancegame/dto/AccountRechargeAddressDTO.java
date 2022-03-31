package io.renren.modules.binancegame.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.base.dto.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 用户充值地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 17:49:45
 */
@Data
@TableName("account_recharge_address")
@ApiModel("用户充值地址")
@Accessors(chain = true)
public class AccountRechargeAddressDTO extends PageParam implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Long id;
	/**
	 * 玩家id
	 */
	@ApiModelProperty(required=false,value="玩家id")
	private Long accountId;
	/**
	 * 地址
	 */
	@ApiModelProperty(required=false,value="地址")
	private String address;
	/**
	 * 钱包余额
	 */
	@ApiModelProperty(required=false,value="钱包余额")
	private BigInteger balanceOf;
	/**
	 * 私钥
	 */
	@ApiModelProperty(required=false,value="私钥")
	private String privateKey;
	/**
	 * 钱包序号
	 */
	@ApiModelProperty(required=false,value="钱包序号")
	private Integer walletSerialNumber;
	/**
	 * 币种类
	 */
	@ApiModelProperty(required=false,value="币种类")
	private String coinType;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;

}
