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
public enum MoneyChangeType implements BaseEnum {

    ZERO(0,"发起提现","withdrawal"),
    ONE(1,"审核拒绝","Audit failure"),
    TWO(2,"投注结算","Betting settlement"),
    THREE(3,"投注","betting"),
    FOUR(4,"代理结算","Clearing agent"),
    FIVE(5,"转账","transfer"),
    ;

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;
    private String englishvalue;

    MoneyChangeType(Integer key, String value,String englishvalue) {
        this.key = key;
        this.value = value;
        this.englishvalue = englishvalue;
    }
}
