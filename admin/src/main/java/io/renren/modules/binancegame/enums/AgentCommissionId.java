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
public enum AgentCommissionId implements BaseEnum {


    ONE(1,"上级代理"),
    TWO(2,"上上级代理"),
    ;

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;

    AgentCommissionId(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
