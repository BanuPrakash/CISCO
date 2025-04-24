package com.cisco.orderapp.service;

import com.cisco.orderapp.dto.UserDTO;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(url = "/users", accept = "application/json", contentType = "application/json")
public interface UserDTOService {
    @GetExchange
    List<UserDTO> getUsers();
}
