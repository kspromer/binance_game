package io.renren.modules.app.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/8 14:12
 */
@Data
@Accessors(chain = true)
public class AccountTeamTotalInformationVO {

    private static final long serialVersionUID = 1L;

    private String title;

    private String icon;

    private BigDecimal value;
}
