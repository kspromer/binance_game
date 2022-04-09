package io.renren.modules.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/8 14:12
 */
@Data
@Accessors(chain = true)
public class AccountShareInformationVO {
    private static final long serialVersionUID = 1L;
    /**
     * 邀请码
     */
    @ApiModelProperty(required=false,value="邀请码")
    private String inviteCode;
    /**
     * 下载的url
     */
    @ApiModelProperty(required=false,value="下载的url")
    private String url;

    public String getUrl() {
        return String.format("https://www.baidu.com?inviteCode=%s",inviteCode);
    }
}
