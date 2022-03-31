package io.renren.modules.binancegame.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 15:01:17
 */
@Data
@TableName("bet_record")
@ApiModel("")
@Accessors(chain = true)
public class BetRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long id;
	/**
	 * 玩家id
	 */
	@ApiModelProperty(required=false,value="玩家id")
	private Long accountId;
	/**
	 * 金额
	 */
	@ApiModelProperty(required=false,value="金额")
	private BigDecimal money;
	/**
	 * 点数
	 */
	@ApiModelProperty(required=false,value="点数")
	private String point;
	/**
	 * 期号
	 */
	@ApiModelProperty(required=false,value="期号")
	private String issueNo;
	/**
	 * 结果
	 */
	@ApiModelProperty(required=false,value="结果")
	private BigDecimal result;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;
	/**
	 * 状态0->投注未结算1->投注已结算
	 */
	@ApiModelProperty(required=false,value="状态0->投注未结算1->投注已结算")
	private Integer state;
	/**
	 * 交易对
	 */
	@ApiModelProperty(required=false,value="交易对")
	private String symbol;
	/**
	 * 赔率
	 */
	@ApiModelProperty(required=false,value="赔率")
	private BigDecimal odds;

}
