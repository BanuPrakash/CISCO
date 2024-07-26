package com.cisco.orderapp.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requests -> requests.requestMatchers("api/products/**")
                .hasAnyRole("READ", "ADMIN")
                .requestMatchers("api/orders/**").hasRole("ADMIN")
                .requestMatchers("/").permitAll())
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("sam")
                        .password("{noop}secret")
                        .authorities("ROLE_READ").build(),
                User.withUsername("jane")
                        .password("{noop}secret")
                        .authorities("ROLE_READ", "ROLE_ADMIN").build()
        );
    }
}
