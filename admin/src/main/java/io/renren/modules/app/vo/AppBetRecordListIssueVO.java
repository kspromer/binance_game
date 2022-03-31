package io.renren.modules.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/22 18:25
 */
@Data
public class AppBetRecordListIssueVO {
    public static void main(String[] args) {
        BigDecimal init = BigDecimal.valueOf(1000);
        for (int i = 0; i < 365; i++) {
            init = init.multiply(BigDecimal.valueOf(0.99));
        }
        System.out.println(init);
    }
    /**
     * 期号
     */
    @ApiModelProperty(required=false,value="期号")
    private String issueNo;
    /**
     * 期号
     */
    @ApiModelProperty(required=false,value="期号")
    private BigDecimal close;
    /**
     * 交易对
     */
    @ApiModelProperty(required=false,value="交易对")
    private String symbol;

    private List<AppBetRecordListVO> appBetRecordListVOS;
}
