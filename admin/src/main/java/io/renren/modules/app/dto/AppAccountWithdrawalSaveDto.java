package io.renren.modules.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/30 16:02
 */
@Data
public class AppAccountWithdrawalSaveDto {
    /**
     * 玩家id
     */
    @ApiModelProperty(required=false,value="玩家id")
    private Long accountId;
    /**
     * 提现金额
     */
    @ApiModelProperty(required=false,value="提现金额")
    private BigDecimal money;
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
}
