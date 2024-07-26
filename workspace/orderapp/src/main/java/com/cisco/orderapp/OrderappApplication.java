package com.cisco.orderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@EnableCaching
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class OrderappApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderappApplication.class, args);
	}

}
