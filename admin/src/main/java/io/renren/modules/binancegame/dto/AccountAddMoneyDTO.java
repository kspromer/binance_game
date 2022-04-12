package io.renren.modules.binancegame.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/12 14:47
 */
@Data
@TableName("account")
@ApiModel("")
@Accessors(chain = true)
public class AccountAddMoneyDTO {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(required=false,value="id")
    private Long id;
    /**
     * 余额
     */
    @ApiModelProperty(required=false,value="余额")
    private BigDecimal addMoney;
}
