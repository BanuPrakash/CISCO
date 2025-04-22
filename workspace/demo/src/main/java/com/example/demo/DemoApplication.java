package com.example.demo;

import com.example.demo.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
       ApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args);

        AppService service = ctx.getBean("appService", AppService.class);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> service.insert());
        executorService.submit(() -> service.insert());
        executorService.submit(() -> service.insert());
        executorService.submit(() -> service.insert());

        executorService.shutdown();


//        System.out.println("******");
//        String[] beans = ctx.getBeanDefinitionNames();
//        for(String name : beans) {
//            System.out.println(name);
//        }
    }

}
