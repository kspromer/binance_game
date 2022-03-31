package com.binance.client.examples.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.market.Candlestick;
import com.binance.client.model.market.ExchangeInformation;
import com.binance.client.model.market.OrderBook;

import java.util.List;

public class GetExchangeInformation {
    public static void main(String[] args) {
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", "19180");
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "19180");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
//        OrderBook usdteth = syncRequestClient.getOrderBook("ETHUSDT", 20);
//        System.out.println(usdteth);
//        ExchangeInformation exchangeInformation = syncRequestClient.getExchangeInformation();
//        System.out.println(exchangeInformation);
        List<Candlestick> ethusdt = syncRequestClient.getCandlestick("ETHUSDT", CandlestickInterval.HOURLY, null, null, 61);

        System.out.println(JSON.toJSON(ethusdt).toString());

        for (Candlestick candlestick : ethusdt) {

        }
    }
}
