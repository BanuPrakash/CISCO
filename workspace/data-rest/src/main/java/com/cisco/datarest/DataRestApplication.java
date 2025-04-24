package com.cisco.datarest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class DataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataRestApplication.class, args);
    }

}
