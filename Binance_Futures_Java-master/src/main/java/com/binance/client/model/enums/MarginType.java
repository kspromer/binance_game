package com.binance.client.model.enums;


public enum MarginType {
    ISOLATED("ISOLATED"),
    CROSSED("CROSSED");

    private final String code;

    MarginType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
