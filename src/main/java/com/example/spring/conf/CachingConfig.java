package com.example.spring.conf;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean("cacheManager")
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("customer");
    }
    
}
