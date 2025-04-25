package com.cisco.orderapp.dto;


import com.cisco.orderapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;
}
