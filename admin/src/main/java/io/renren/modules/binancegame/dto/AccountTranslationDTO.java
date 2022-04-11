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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户转账
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-04-11 15:00:13
 */
@Data
@TableName("account_translation")
@ApiModel("用户转账")
@Accessors(chain = true)
public class AccountTranslationDTO extends PageParam implements Serializable {
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
	 * 转账玩家id
	 */
	@ApiModelProperty(required=false,value="转账玩家id")
	private Long toAccountId;
	/**
	 * 用户名
	 */
	@ApiModelProperty(required=false,value="用户名")
	private String toUsername;
	/**
	 * 提现金额
	 */
	@ApiModelProperty(required=false,value="提现金额")
	private BigDecimal money;
	/**
	 * 备注
	 */
	@ApiModelProperty(required=false,value="备注")
	private String note;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;

}
