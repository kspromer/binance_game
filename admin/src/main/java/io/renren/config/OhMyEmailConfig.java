package io.renren.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuyuchan
 * @email liuyuchan@qq.com
 * @date 2020-01-09 13:48
 */
@Component
@ConfigurationProperties(prefix="ohmyemail")
@Data
public class OhMyEmailConfig {

    private String username;

    private String password;

}
