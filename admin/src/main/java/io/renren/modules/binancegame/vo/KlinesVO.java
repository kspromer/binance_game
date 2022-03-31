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
 * @date 2022-03-21 16:16:17
 */
@Data
@TableName("klines")
@ApiModel("")
@Accessors(chain = true)
public class KlinesVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 期号：YYYYMMDDHHmm
	 */
	@ApiModelProperty(required=false,value="期号：YYYYMMDDHHmm")
	private String issueNo;
	/**
	 * 交易对
	 */
	@ApiModelProperty(required=false,value="交易对")
	private String symbol;
	/**
	 * 收盘价
	 */
	@ApiModelProperty(required=false,value="收盘价")
	private BigDecimal close;
	/**
	 * 状态：0->下注 1->结算 2->关闭
	 */
	@ApiModelProperty(required=false,value="状态：0->下注 1->结算 2->关闭")
	private Integer state;
	/**
	 * 结果
	 */
	@ApiModelProperty(required=false,value="结果")
	private String result;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(required=false,value="开始时间")
	private Date openTime;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(required=false,value="结束时间")
	private Date closeTime;
	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="")
	private Long id;

}
