package com.cisco.orderapp.api;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    OrderService service;

    //GET http://localhost:8080/api/products?low=100&high=5000
    //GET http://localhost:8080/api/products
    @GetMapping
    public List<Product> getProducts(@RequestParam(name = "low", defaultValue = "0.0") double low,
                                     @RequestParam(name ="high", defaultValue = "0.0") double high) {
        if(low == 0.0 && high == 0.0) {
            return service.getProducts();
        }
        else {
            return service.byRange(low, high);
        }
    }

    // GET http://localhost:8080/api/products/3
    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") int id) throws ResourceNotFoundException{
        return service.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Product addProduct(@RequestBody @Valid Product p) {
        return service.saveProduct(p);
    }

    // PUT http://localhost:8080/api/products/3
    @PutMapping("/{id}")
    public  Product updateProduct(@PathVariable("id") int id, @RequestBody Product p) throws ResourceNotFoundException{
            service.updateProduct(id, p);
            return service.getProductById(id);
    }

}
