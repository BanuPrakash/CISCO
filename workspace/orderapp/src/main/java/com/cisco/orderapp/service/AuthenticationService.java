package com.cisco.orderapp.service;

import com.cisco.orderapp.dto.SignInRequest;
import com.cisco.orderapp.dto.SignUpRequest;
import com.cisco.orderapp.entity.User;
import com.cisco.orderapp.repo.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userDao;
    private final JwtService jwtService;
    private  final PasswordEncoder passwordEncoder;

    // register
    public  String signUp(SignUpRequest request) {
        User user = User.builder().
                email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())).
                roles(request.getRoles()).
                build();
        userDao.save(user);
        String token = jwtService.generateToken(user);
        return token;
    }

    // login
    public  String login(SignInRequest request) {
        // TODO use AuthenticationManager to validate, validate token..
        User user = userDao.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email/pwd"));
        String token = jwtService.generateToken(user);
        return token;
    }
}
