package com.cisco.orderapp.api;

import com.cisco.orderapp.dto.PostDTO;
import com.cisco.orderapp.dto.UserDTO;
import com.cisco.orderapp.service.AggregatorService;
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
    private final AggregatorService service;

    record PostUserDTO(String title, String email) {}

    @GetMapping()
    public List<PostUserDTO> getPostsAPI() {
        CompletableFuture<List<PostDTO>> posts = service.getPosts(); // non blocking
        CompletableFuture<List<UserDTO>> users = service.getUsers(); // non blocking

        // barrier
       List<PostDTO> postList =  posts.join();
       List<UserDTO> userList = users.join();

        return postList.stream().map(post -> {
            String email = userList.stream()
                    .filter(user -> user.id() == post.userId())
                    .findFirst().get().email();
            return  new PostUserDTO(post.title(), email);
        }).collect(Collectors.toList());
    }
}
