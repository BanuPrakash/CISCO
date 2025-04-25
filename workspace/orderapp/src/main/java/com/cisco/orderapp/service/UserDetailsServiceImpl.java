package com.cisco.orderapp.service;

import com.cisco.orderapp.repo.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {
    private final UserDao userDao;
    public UserDetailsService userDetailsService() {
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // username passed will be email of user
                return userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not fount"));
            }
        };
    }
}
