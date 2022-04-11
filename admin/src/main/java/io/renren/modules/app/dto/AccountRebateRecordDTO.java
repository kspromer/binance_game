package io.renren.modules.app.dto;

import io.renren.common.base.dto.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/9 16:03
 */
@Data
@Accessors(chain = true)
public class AccountRebateRecordDTO extends PageParam {
    /**
     * 玩家id
     */
    @ApiModelProperty(required=false,value="玩家id")
    private Long accountId;
    /**
     * 类型
     */
    @ApiModelProperty(required=false,value="类型")
    private Integer type;
}
