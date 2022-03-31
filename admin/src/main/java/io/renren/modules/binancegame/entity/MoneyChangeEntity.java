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
 * 金额变动
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 16:18:09
 */
@Data
@TableName("money_change")
@ApiModel("金额变动")
@Accessors(chain = true)
public class MoneyChangeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long id;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 类型
	 */
	@ApiModelProperty(required=false,value="类型")
	private Integer type;
	/**
	 * 详情
	 */
	@ApiModelProperty(required=false,value="详情")
	private String description;
	/**
	 * 变动金额
	 */
	@ApiModelProperty(required=false,value="变动金额")
	private BigDecimal amount;
	/**
	 * 用户id
	 */
	@ApiModelProperty(required=false,value="用户id")
	private Long accountId;
	/**
	 * 变动前
	 */
	@ApiModelProperty(required=false,value="变动前")
	private BigDecimal beforeMoney;
	/**
	 * 变动后
	 */
	@ApiModelProperty(required=false,value="变动后")
	private BigDecimal afterMoney;

}
