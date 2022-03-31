package com.binance.client.examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.enums.*;

public class PostOrder {
    public static void main(String[] args) {
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", "19180");
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
//        System.out.println(syncRequestClient.postOrder("BTCUSDT", OrderSide.SELL, PositionSide.BOTH, OrderType.LIMIT, TimeInForce.GTC,
//                "1", "1", null, null, null, null));
//卖出
//{"symbol":"1000SHIBUSDT","type":"MARKET","side":"SELL","quantity":218,"positionSide":"LONG","leverage":10,"newOrderRespType":"RESULT","placeType":"position"}
//{"symbol":"1000SHIBUSDT","type":"LIMIT","side":"BUY","positionSide":"LONG","quantity":223,"timeInForce":"GTC","price":"0.026862","placeType":"order-form"}

//{"symbol":"1000SHIBUSDT","type":"LIMIT","side":"SELL","positionSide":"SHORT","quantity":253,"timeInForce":"GTC","price":"0.023694","placeType":"order-form"}
//{"symbol":"1000SHIBUSDT","type":"MARKET","side":"BUY","positionSide":"SHORT","quantity":253,"placeType":"order-form"}

//{"symbol":"1000SHIBUSDT","type":"LIMIT","side":"BUY","positionSide":"LONG","quantity":252,"timeInForce":"GTC","price":"0.023735","placeType":"order-form"}
//{"symbol":"1000SHIBUSDT","type":"MARKET","side":"BUY","positionSide":"LONG","quantity":252,"placeType":"order-form"}
//{"symbol":"1000SHIBUSDT","type":"MARKET","side":"SELL","positionSide":"LONG","quantity":252,"placeType":"order-form"}

//{"symbol":"DOGEUSDT","side":"SELL","positionSide":"LONG","type":"TAKE_PROFIT_MARKET","timeInForce":"GTE_GTC","quantity":0,"stopPrice":"0.12863","workingType":"MARK_PRICE","closePosition":true,"placeType":"position"}
        // place dual position side order.
        // Switch between dual or both position side, call: com.binance.client.examples.trade.ChangePositionSide
        System.out.println(syncRequestClient.postOrder("1000SHIBUSDT", OrderSide.BUY, PositionSide.LONG, OrderType.LIMIT, TimeInForce.GTC,
                "218", "0.026909", null, null, null, null, NewOrderRespType.RESULT,false,null));


//        syncRequestClient.getOrder()

//        syncRequestClient.getAllOrders()
    }
}
