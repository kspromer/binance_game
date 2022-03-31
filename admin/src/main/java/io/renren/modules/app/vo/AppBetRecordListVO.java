package io.renren.modules.app.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/22 18:25
 */
@Data
public class AppBetRecordListVO {

    /**
     * 金额
     */
    @ApiModelProperty(required=false,value="金额")
    private BigDecimal money;
    /**
     * 期号
     */
    @ApiModelProperty(required=false,value="期号")
    private String issueNo;
    /**
     * id
     */
    @ApiModelProperty(required=false,value="id")
    private Long id;
    /**
     * 点数
     */
    @ApiModelProperty(required=false,value="点数")
    private String point;
    /**
     * 结果
     */
    @ApiModelProperty(required=false,value="结果")
    private BigDecimal result;
    /**
     * 交易对
     */
    @ApiModelProperty(required=false,value="交易对")
    private String symbol;
}
