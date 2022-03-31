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
 * 消息
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-30 13:44:46
 */
@Data
@TableName("message")
@ApiModel("消息")
@Accessors(chain = true)
public class MessageEntity implements Serializable {
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
	 * 标题
	 */
	@ApiModelProperty(required=false,value="标题")
	private String title;
	/**
	 * 详情
	 */
	@ApiModelProperty(required=false,value="详情")
	private String detail;
	/**
	 * 类型
	 */
	@ApiModelProperty(required=false,value="类型")
	private Integer type;
	/**
	 * 类型中文
	 */
	@ApiModelProperty(required=false,value="类型中文")
	private String typeStr;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(required=false,value="创建时间")
	private Date createTime;

}
