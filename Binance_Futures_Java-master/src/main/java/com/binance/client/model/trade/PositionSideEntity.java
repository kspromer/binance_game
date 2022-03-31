package com.binance.client.model.trade;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/2/21 23:09
 */
public class PositionSideEntity {

    private boolean dualSidePosition;


    public boolean isDualSidePosition() {
        return dualSidePosition;
    }

    public void setDualSidePosition(boolean dualSidePosition) {
        this.dualSidePosition = dualSidePosition;
    }
}
