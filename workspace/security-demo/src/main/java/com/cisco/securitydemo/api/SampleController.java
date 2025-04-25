package com.cisco.securitydemo.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.DelegatingFilterProxy;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String getGreeting() {
        return "Hello User";
    }

    @GetMapping("/admin")
    public String greetingAdmin() {
        return "Hello Admin";
    }
}
