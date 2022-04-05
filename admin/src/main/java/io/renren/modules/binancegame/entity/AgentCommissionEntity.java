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
 * 分佣配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-04 18:15:07
 */
@Data
@TableName("agent_commission")
@ApiModel("分佣配置")
@Accessors(chain = true)
public class AgentCommissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(required=false,value="id")
	private Long id;
	/**
	 * 比例
	 */
	@ApiModelProperty(required=false,value="比例")
	private BigDecimal proportion;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;

}
