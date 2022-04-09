package io.renren.modules.binancegame.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.renren.common.base.interfaces.BaseEnum;
import lombok.Getter;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/4/8 14:37
 */
@Getter
public enum InviteRewardsCard implements BaseEnum {
    ZERO(1,"邀请人数","binance_game_app_assets/images/home_assets_invite_rewards_numbers_icon.png.png"),
    ONE(2,"我的返利(USDT)","binance_game_app_assets/images/home_assets_invite_rewards_rebate_icon.png"),
    ;

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;
    private String icon;
    InviteRewardsCard(Integer key, String value,String icon) {
        this.key = key;
        this.value = value;
        this.icon = icon;
    }
}
