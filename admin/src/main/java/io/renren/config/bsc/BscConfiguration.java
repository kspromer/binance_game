package io.renren.config.bsc;

import io.renren.common.utils.wallet.WalletClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;


/**
 * 默认配置
 */
@Configuration
public class BscConfiguration {

    @Autowired
    private Web3jConfig web3jConfig;


    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(web3jConfig.getNetworkUrl()));
    }

    @Bean
    public StaticGasProvider defaultGasProvider() {
        return new DyProvider();
    }


}
