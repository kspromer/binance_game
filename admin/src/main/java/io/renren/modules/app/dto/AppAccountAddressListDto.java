package io.renren.modules.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/30 15:42
 */
@Data
public class AppAccountAddressListDto {
    /**
     * 玩家id
     */
    @ApiModelProperty(required=false,value="玩家id")
    private Long accountId;
    /**
     * 币种类
     */
    @ApiModelProperty(required=false,value="币种类")
    private String coinType;
}
