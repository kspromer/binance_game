package io.renren.modules.app.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/9 16:05
 */
@Data
@Accessors(chain = true)
public class AccountRebateRecordVO {

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
