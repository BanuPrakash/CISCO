package com.example.demo;

import com.example.demo.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
       ApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args);

        AppService service = ctx.getBean("appService", AppService.class);
        service.insert();
    }

}
