package io.renren.modules.app.dto;

import io.renren.common.base.dto.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/3/30 13:47
 */
@Data
public class AppMessageListDto extends PageParam {
    /**
     * 玩家id
     */
    @ApiModelProperty(required=false,value="玩家id")
    private Long accountId;
}
