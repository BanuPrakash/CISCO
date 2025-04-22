package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient implements CommandLineRunner {
    private final OrderService orderService;

    // this method is called by Spring once all beans are initialized within spring container
    @Override
    public void run(String... args) throws Exception {
        printProducts();
        System.out.println("****");
        getProductById();
    }

    private void getProductById() {
        Product p = orderService.getProductById(1);
        System.out.println(p);
    }

    private void printProducts() {
        List<Product> products = orderService.getProducts();
        for(Product p : products) {
            System.out.println(p);
        }
    }
}
