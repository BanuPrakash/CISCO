package com.cisco.orderapp.api;

import com.cisco.orderapp.dto.SignInRequest;
import com.cisco.orderapp.dto.SignUpRequest;
import com.cisco.orderapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthenticationService service;

    @PostMapping("/register")
    public String register(@RequestBody SignUpRequest request) {
        System.out.println("Entered!!!");
        return  service.signUp(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody SignInRequest request) {
        return  service.login(request);
    }
}
