package com.cisco.securitydemo.cfg;

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
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("brad")
                        .password("{noop}secret123")
                        .authorities("ROLE_GUEST").build(),
                User.withUsername("rita")
                        .password("{noop}secret123")
                        .authorities("ROLE_ADMIN", "ROLE_GUEST").build()
        );
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws  Exception {
        httpSecurity.authorizeHttpRequests(requests -> requests.requestMatchers("/admin")
                .hasRole("ADMIN")
                .requestMatchers("/hello").hasAnyRole("ADMIN", "GUEST")
                .requestMatchers("/").permitAll())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
