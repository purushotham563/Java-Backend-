package io.backend.Spring_Boot_Caffeine.layer;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.*;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;


@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager=new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(1000).
                expireAfterWrite(Duration.ofMinutes(10)).
                expireAfterAccess(Duration.ofMinutes(5)).recordStats());
        List<String>names=new ArrayList<>();
        names.add("user");
        names.add("products");
        names.add("orders");
        cacheManager.setCacheNames(names);
        return cacheManager;
    }
    @Bean
    public Caffeine<Object,Object>caffeineConfig(){
        return Caffeine.newBuilder().maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(5)).recordStats();
    }

}
