package com.cisco.orderapp.api;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    OrderService service;

    @GetMapping
    public List<Product> getProducts() {
        return  service.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Product addProduct(@RequestBody Product p) {
        return service.saveProduct(p);
    }

}
