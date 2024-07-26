package com.cisco.asyncdemo.api;

import com.cisco.asyncdemo.dto.Post;
import com.cisco.asyncdemo.dto.User;
import com.cisco.asyncdemo.service.PostServiceAggregator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostServiceAggregator aggregator;

    record PostDTO(String title, String name) {}

    @GetMapping
    public List<PostDTO> getPostsAndUser() {
        CompletableFuture<List<Post>> posts = aggregator.getPosts(); // concurrently
        CompletableFuture<List<User>> users = aggregator.getUsers(); // concurrently
        // barrier --> join()
        long startTime = System.currentTimeMillis();
        List<Post> postList = posts.join(); // blocked
        List<User> userList = users.join(); // blocked
        long endTime = System.currentTimeMillis();
        System.out.println( (endTime - startTime ) + " ms");
        // combine and send Response [ like flights and hotels ]
        return postList.stream().map(post -> {
            // get user for a post
            String username = userList.stream()
                    .filter(user -> user.id() == post.userId()).findFirst().get().name();
            return new PostDTO(post.title(), username);
        }).collect(Collectors.toList());
    }
}
