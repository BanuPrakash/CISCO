package com.cisco.asyncdemo.service;

import com.cisco.asyncdemo.dto.Post;
import com.cisco.asyncdemo.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PostServiceAggregator {
    private final PostInterface postInterface;
    private final UserInterface userInterface;

    @Async("postsThreadPool")
    public CompletableFuture<List<Post>> getPosts() {
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture(postInterface.getPosts());
    }

    @Async("usersThreadPool")
    public CompletableFuture<List<User>> getUsers() {
        System.out.println(Thread.currentThread());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture(userInterface.getUsers());
    }
}
