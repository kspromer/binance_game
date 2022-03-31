package io.renren.config;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2022/2/24 17:51
 */
@Configuration
public class SyncRequestClientConfigration {

    @Bean
    public SyncRequestClient syncRequestClient() {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);;
        return syncRequestClient;
    }
}
