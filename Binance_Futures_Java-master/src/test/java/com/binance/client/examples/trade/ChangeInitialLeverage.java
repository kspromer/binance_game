package com.binance.client.examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;

public class ChangeInitialLeverage {
    public static void main(String[] args) {
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", "19180");
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
//        System.out.println(syncRequestClient.changeInitialLeverage("BTCUSDT", 1));

        System.out.println(syncRequestClient.getPositionRisk("BTCUSDT"));
//        System.out.println(syncRequestClient.getInitialLeverage("BTCUSDT"));
    }
}
