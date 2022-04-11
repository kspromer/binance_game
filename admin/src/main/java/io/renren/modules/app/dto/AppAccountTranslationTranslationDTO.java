package io.renren.modules.app.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/11 15:02
 */
@Data
public class AppAccountTranslationTranslationDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 玩家id
     */
    @ApiModelProperty(required=false,value="玩家id")
    private Long accountId;
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
}
