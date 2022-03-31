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
public enum WithdrawalStatus implements BaseEnum {

    ZERO(0,"提交审核"),
    ONE(1,"审核成功"),
    TWO(2,"审核失败");

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;

    WithdrawalStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
