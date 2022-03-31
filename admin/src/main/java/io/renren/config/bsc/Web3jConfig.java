package io.renren.config.bsc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuyuchan
 * @email liuyuchan286@gmail.com
 * @date 2021/11/30 21:15
 */
@Component
@ConfigurationProperties(prefix="web3j")
@Data
public class Web3jConfig {
    private Byte chainId;
    private String networkUrl;
    private String contractAddress;
}
