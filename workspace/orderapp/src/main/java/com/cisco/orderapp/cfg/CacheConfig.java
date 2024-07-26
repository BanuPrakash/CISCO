package com.cisco.orderapp.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CacheConfig {
    @Autowired
    CacheManager cacheManager;
//https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
    //@Scheduled(fixedRate = 2000)

    // every half-hour
   @Scheduled(cron = "0 0/30 * * * *")
    public void clearCache() {
        System.out.println("Cache Cleared!!!");
        cacheManager.getCacheNames().forEach(cache -> {
            if(cache.equals("productCache")) {
                cacheManager.getCache(cache).clear();
            }
        });
    }

}
