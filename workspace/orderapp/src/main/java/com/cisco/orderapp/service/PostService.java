package com.cisco.orderapp.service;

import com.cisco.orderapp.dto.PostDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(url = "/posts", accept = "application/json", contentType = "application/json")
public interface PostService {
    @GetExchange
    List<PostDTO> getPosts();

    @GetExchange("/{id}")
    PostDTO getPostById(@PathVariable("id") int id);

    @PostMapping
    PostDTO addPost(@RequestBody PostDTO postDTO);
}
