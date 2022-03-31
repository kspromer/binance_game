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
public enum CoinType implements BaseEnum {

    ZERO(0,"BEP20"),
    ONE(1,"TRC20"),
    ;

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;

    CoinType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
