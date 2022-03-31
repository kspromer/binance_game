package io.renren.modules.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/30 18:37
 */
@Data
public class AccountRechargeAddressGetAddressVO {
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
