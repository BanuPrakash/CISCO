package com.cisco.orderapp.service;

import com.cisco.orderapp.dto.PostDTO;
import com.cisco.orderapp.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregatorService {
    private final PostService postService; // impl from AppConfig
    private final UserDTOService userSerice; // impl from AppConfig

    @Async("users-pool")
    public CompletableFuture<List<UserDTO>> getUsers() {
        log.info("get users " + Thread.currentThread());
        return CompletableFuture.completedFuture(userSerice.getUsers());
    }

    @Async("posts-pool")
    public CompletableFuture<List<PostDTO>> getPosts() {
        log.info("get pposts " + Thread.currentThread());
        return  CompletableFuture.completedFuture(postService.getPosts());
    }
}
