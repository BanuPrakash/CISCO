package com.cisco.orderapp.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
@EnableScheduling
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class AppConfig {
    @Autowired
    CacheManager cacheManager;


    @Scheduled(cron = "0 0/30 * * * *")
//    @Scheduled(fixedRate = 1000)
    public void clearCache() {
        System.out.println("Cache Cleared!!!");
        cacheManager.getCacheNames().forEach(cache -> {
            cacheManager.getCache(cache).clear();
        });
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // allow you to add headers like JWT token
        // headers
        return builder.build();
    }
}
