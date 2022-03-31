package io.renren.modules.binancegame.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.renren.common.base.interfaces.BaseEnum;
import lombok.Getter;

/**
 * 投注配置
 *
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2022-03-22 11:26:37
 */
@Getter
public enum KlinesState implements BaseEnum {

    ZERO(0,"投注中"),
    ONE(1,"停止投注"),
    TWO(2,"结算完成");

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;

    KlinesState(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
