package io.renren.modules.app.vo;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/22 15:13
 */
@Data
public class AppKlinesCurrentIssueNoVO {
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
    @TableField(value = "`close`")
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
    private Long openTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(required=false,value="结束时间")
    private Long closeTime;
    /**
     * 倒计时
     */
    @ApiModelProperty(required=false,value="倒计时")
    private Long countdown;
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(required=false,value="")
    private Long id;

    public Long getCountdown() {
        return DateUtil.between(DateUtil.date(),DateUtil.date(this.closeTime), DateUnit.SECOND);
    }

    public String getIssueNo() {
        if (StrUtil.isNotEmpty(issueNo)) {
            return issueNo;
        }
        return DateUtil.format(DateUtil.date(this.openTime),"YYMMddHHmm");
    }

    public String getResult() {
        if (StrUtil.isNotEmpty(result)) {
            return result;
        }
        int resultClose = this.close.multiply(BigDecimal.valueOf(100)).intValue();
        int i1 = resultClose % 10;
        int i2 = resultClose / 10 % 10;
        int i3 = resultClose / 100 % 10;
        int number = i1 + i2 + i3;
        return String.valueOf(number % 10);
    }
}
