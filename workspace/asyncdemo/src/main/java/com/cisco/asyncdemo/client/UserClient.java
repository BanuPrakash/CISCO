package com.cisco.asyncdemo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient implements CommandLineRunner {
    @Autowired
    RestTemplate template;

    record Comment(int postId, int id, String name, String email, String body) {}

    @Override
    public void run(String... args) throws Exception {
        getUsers();
        getPost();
        addProduct();
    }

    private void addProduct() {
       // Product p = Product.builder.name("A").price(3434.33).build();
//        ResponseEntity<Product>  response =
//                template.postForEntity("http://localhost:8080/api/products", p, Prdoduct.class);
    }

    private void getPost() {
        ResponseEntity<Comment> response =
                template.getForEntity("https://jsonplaceholder.typicode.com/comments/4", Comment.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    private void getUsers() {
        String result = template.getForObject("https://jsonplaceholder.typicode.com/users", String.class);
        System.out.println(result);
    }
}
