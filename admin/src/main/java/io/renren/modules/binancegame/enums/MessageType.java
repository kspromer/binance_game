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
public enum MessageType implements BaseEnum {

    ZERO(0,"发起提现","Withdrawal","Your withdrawal has been initiated,Your withdrawal has been initiated, pending administrator review"),
    ONE(1,"审核通过","Withdrawal success","Your withdrawal was successful"),
    TWO(2,"审核失败","Withdrawal failure","Your withdrawal was not approved"),
    ;

    @EnumValue
    @JsonValue
    private Integer key;
    private String value;
    private String title;
    private String detail;

    MessageType(Integer key, String value, String title, String detail) {
        this.key = key;
        this.value = value;
        this.title = title;
        this.detail = detail;
    }
}
