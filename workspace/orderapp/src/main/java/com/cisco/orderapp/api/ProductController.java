package com.cisco.orderapp.api;


import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    private final OrderService orderService;

    // GET
    // http://localhost:8080/api/products
    // accept: application/json
    @GetMapping()
    public List<Product> getProducts() {
       return  orderService.getProducts();
    }

    // POST
    // http://localhost:8080/api/products
    // content-type:application/json
    // accept: application/json
    /*
        {
            name: "Wacom",
            price: 5400,
            quantity: 100
        }
     */

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) // 201
    public  Product addProduct(@RequestBody Product p) {
        return  orderService.saveProduct(p);
    }
}
