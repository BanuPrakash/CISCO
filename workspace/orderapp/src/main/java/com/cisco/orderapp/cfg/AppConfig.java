package com.cisco.orderapp.cfg;


import com.cisco.orderapp.service.PostService;
import com.cisco.orderapp.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableCaching
@EnableScheduling
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
@EnableAsync
public class AppConfig {
    @Autowired
    CacheManager cacheManager;

    // create thread pools
    @Bean(name="posts-pool")
    public Executor asyncExectorPosts() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(25);
        executor.setThreadNamePrefix("POSTS-THREAD-POOL:");
        executor.initialize();
        return executor;
    }

    @Bean(name="users-pool")
    public Executor asyncExectorUsers() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(25);
        executor.setThreadNamePrefix("USERS-THREAD-POOL:");
        executor.initialize();
        return executor;
    }

    @Bean
    public UserDTOService userDTOService() {
        // RestTemplate, WebClient, RestClient
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserDTOService.class);
    }


    @Bean
    public PostService postService() {
        // RestTemplate, WebClient, RestClient
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(PostService.class);
    }

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
