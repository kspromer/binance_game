package io.renren.modules.app.dto;

import io.renren.common.base.dto.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/9 15:44
 */
@Data
@Accessors(chain = true)
public class AccountInviterListDTO extends PageParam {
    /**
     * 玩家id
     */
    @ApiModelProperty(required=false,value="玩家id")
    private Long accountId;
}

