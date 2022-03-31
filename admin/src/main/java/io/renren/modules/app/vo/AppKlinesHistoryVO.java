package io.renren.modules.binancegame.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 *
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-21 16:16:17
 */
@Data
@TableName("klines")
@ApiModel("")
@Accessors(chain = true)
public class AppKlinesHistoryVO implements Serializable {

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
     * 结果
     */
    @ApiModelProperty(required=false,value="结果")
    private String result;
    /**
     * 收盘价
     */
    @ApiModelProperty(required=false,value="收盘价")
    @TableField(value = "`close`")
    private BigDecimal close;
}
