package io.renren.config.bsc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tron.trident.core.ApiWrapper;
import org.tron.trident.core.contract.Contract;
import org.tron.trident.core.contract.Trc20Contract;
import org.tron.trident.core.key.KeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;


/**
 * 默认配置
 */
@Configuration
public class TrxConfiguration {

    @Bean
    public Trc20Contract trc20Contract() {
        ApiWrapper wrapper = new ApiWrapper("grpc.trongrid.io:50051", "grpc.trongrid.io:50052", KeyPair.generate().toPrivateKey());
        Contract contract = wrapper.getContract("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t");
        Trc20Contract token = new Trc20Contract(contract, "THPvaUhoh2Qn2y9THCZML3H815hhFhn5YC", wrapper);
        return token;
    }

}
