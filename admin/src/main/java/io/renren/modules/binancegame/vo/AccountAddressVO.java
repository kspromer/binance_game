package io.renren.modules.binancegame.vo;

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
 * 用户地址
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 15:39:21
 */
@Data
@TableName("account_address")
@ApiModel("用户地址")
@Accessors(chain = true)
public class AccountAddressVO implements Serializable {
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
	 * 币种类
	 */
	@ApiModelProperty(required=false,value="币种类")
	private String coinType;
	/**
	 * 标签
	 */
	@ApiModelProperty(required=false,value="标签")
	private String label;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;

}
