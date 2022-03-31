package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

public enum  TimeInForce {
    GTE_GTC("GTE_GTC"),
    GTC("GTC"),
    GTE("GTE"),
    IOC("IOC"),
    FOK("FOK"),
    GTX("GTX"),
    LIMIT("LIMIT"),
    MARKET("MARKET"),
    STOP("STOP"),
    STOP_MARKET("STOP_MARKET"),
    TAKE_PROFIT("TAKE_PROFIT"),
    TAKE_PROFIT_MARKET("TAKE_PROFIT_MARKET"),
    INVALID(null);

    private final String code;

    TimeInForce(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    private static final EnumLookup<TimeInForce> lookup = new EnumLookup<>(TimeInForce.class);

    public static TimeInForce lookup(String name) {
        return lookup.lookup(name);
    }
}
