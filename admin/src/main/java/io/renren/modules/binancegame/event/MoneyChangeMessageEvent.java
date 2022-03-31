package io.renren.modules.binancegame.event;

import io.renren.modules.binancegame.enums.MessageType;
import io.renren.modules.binancegame.enums.MoneyChangeType;
import io.renren.modules.binancegame.vo.AccountVO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-10-15 11:34
 */
@Data
@Accessors(chain = true)
public class MoneyChangeMessageEvent extends ApplicationEvent {

    private Long accountId;
    private BigDecimal money;
    private MessageType messageType;
    private MoneyChangeType moneyChangeType;
    private AccountVO accountVO;

    public MoneyChangeMessageEvent(Object source) {
        super(source);
    }
}
