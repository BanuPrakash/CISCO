package com.cisco.orderapp.api;


import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    // http://localhost:8080/api/products?page=1&size=2
    // http://localhost:8080/api/products?low=5000&high=25000 Query Parameters ?
    // accept: application/json

    @GetMapping()
    public List<Product> getProducts(@RequestParam(value = "low", defaultValue = "0.0") double low,
                                     @RequestParam(value = "high", defaultValue = "0.0") double high,
        @RequestParam(value="size", defaultValue="0") int size,
                                     @RequestParam(value="page", defaultValue="0") int page
        ) {
        if(size != 0 ) {
            return orderService.getProductsByPage(PageRequest.of(page, size)).toList();
        } else if(low == 0.0 && high == 0.0) {
           return orderService.getProducts();
       }
       return  orderService.getByRange(low, high);
    }

    // GET
    // http://localhost:8080/api/products/4 Path Parameter
    // accept: application/json
    @GetMapping("/{pid}")
    public Product getProductById(@PathVariable("pid") int id) {
        return  orderService.getProductById(id);
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

    // PATCH
    // http://localhost:8080/api/products/3?price=52000
    // Accept: application/json

    @PatchMapping ("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestParam("price") double price) {
        return orderService.updateProduct(id, price);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        return "Operation Not supported!!!";
    }
}
