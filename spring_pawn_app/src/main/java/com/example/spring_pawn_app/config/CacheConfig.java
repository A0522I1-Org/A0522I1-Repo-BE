package com.example.spring_pawn_app.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    @Bean
    public ConcurrentMapCacheManager cacheManager(){

        return new ConcurrentMapCacheManager("otpCache");
    }
}
