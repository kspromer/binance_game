package io.renren.modules.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/22 15:50
 */
@Data
public class AppBetRecordBetDTO {
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
//    /**
//     * 期号
//     */
//    @ApiModelProperty(required=false,value="期号")
//    private String issueNo;
//    /**
//     * 状态0->投注未结算1->投注已结算
//     */
//    @ApiModelProperty(required=false,value="状态0->投注未结算1->投注已结算")
//    private Integer state;
//    /**
//     * 交易对
//     */
//    @ApiModelProperty(required=false,value="交易对")
//    private String symbol;
//    /**
//     * 赔率
//     */
//    @ApiModelProperty(required=false,value="赔率")
//    private BigDecimal odds;
}
