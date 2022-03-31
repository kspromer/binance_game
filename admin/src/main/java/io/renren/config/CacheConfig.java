package io.renren.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.renren.modules.app.vo.AppKlinesCurrentIssueNoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class CacheConfig {

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
    }


    @Bean("caffeineAppKlinesCurrentIssueNoVO")
    public Cache<String, AppKlinesCurrentIssueNoVO> caffeineAppKlinesCurrentIssueNoVO() {
        return Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                // 初始的缓存空间大小
                .initialCapacity(1)
                // 缓存的最大条数
                .maximumSize(1)
                .build();
    }

}
