package com.example.spring.conf;

import java.util.Arrays;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    @Profile("test")
    public SimpleCacheManager simpleCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("customer")));
        return cacheManager;
    }
    
    @Bean
    @Profile("dev")
    public ConcurrentMapCacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager("customer");
    }
    
//    @Bean
//    public RedisCacheManager redisCacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setCacheNames(Arrays.asList("customer"));
//        return cacheManager;
//    }
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory =
//                new JedisConnectionFactory();
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF) {
//        RedisTemplate<String, String> redisTemplate =
//                new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(redisCF);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

}
