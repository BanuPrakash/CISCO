package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class ProductClient implements CommandLineRunner {
    @Autowired
    private OrderService service;

    // run method is going to be called
    // as soon as spring container is created and initialized
    @Override
    public void run(String... args) throws Exception {
        getProducts();
    }

    private void getProducts() {
        List<Product> products = service.getProducts();
        for(Product p : products) {
            System.out.println(p);
        }
    }
}
