package com.cisco.orderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableCaching
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class OrderappApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderappApplication.class, args);
	}

}
