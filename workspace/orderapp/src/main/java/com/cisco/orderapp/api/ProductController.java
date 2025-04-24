package com.cisco.orderapp.api;


import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.EntityNotFoundException;
import com.cisco.orderapp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    public Product getProductById(@PathVariable("pid") int id) throws EntityNotFoundException {
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
    @Cacheable(value = "productCache", key = "#p.id", condition = "#p.quantity > 10")
    public @ResponseBody Product addProduct(@RequestBody @Valid  Product p) {
        return  orderService.saveProduct(p);
    }

    // PATCH
    // http://localhost:8080/api/products/3?price=52000
    // Accept: application/json

    @PatchMapping ("/{id}")
    @CachePut(value = "productCache", key = "#id")
    public Product updateProduct(@PathVariable("id") int id, @RequestParam("price") double price) throws EntityNotFoundException {
        return orderService.updateProduct(id, price);
    }

    @PutMapping ("/{id}")
    @CachePut(value = "productCache", key = "#id")
    public Product updateProductPut(@PathVariable("id") int id, @RequestBody Product p) throws EntityNotFoundException {
        return orderService.updateProduct(id, p.getPrice());
    }

//    @PutMapping ("/{id}")
//    public Product updateCompleteProduct(@PathVariable("id") int id, @RequestBody Product p) throws EntityNotFoundException {
////        return orderService.updateProduct(id, price);
//    }
    private class StringWrapper {
        private String str;
        public StringWrapper(String str) {
            this.str = str;
        }

    @Override
    public String toString() {
        return str;
    }
}
    @DeleteMapping("/{id}")
    @CacheEvict(value = "productCache", key = "#id")
    public StringWrapper deleteProduct(@PathVariable("id") int id) {
        return new StringWrapper("Operation Not supported!!!");
    }

    @GetMapping("/etag/{pid}")
    public ResponseEntity<Product> getProductByIdAndEtag(@PathVariable("pid") int id) throws EntityNotFoundException {
        Product p = orderService.getProductById(id);
        return  ResponseEntity.ok().eTag(Long.toString(p.hashCode())).body(p);
    }

    @GetMapping("/cache/{pid}")
    @Cacheable(value = "productCache", key = "#id", unless = "#result == null")
    public Product getProductByCacheId(@PathVariable("pid") int id) throws EntityNotFoundException {
        System.out.println("Cache Miss!!!");
        // simulate delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  orderService.getProductById(id);
    }


    // HATEOAS
    @GetMapping("/hateoas/{pid}")
    public EntityModel<Product> getProductHateosById(@PathVariable("pid") int id) throws EntityNotFoundException {
        Product p =  orderService.getProductById(id);
        EntityModel<Product> entityModel = EntityModel.of(p ,
                linkTo(methodOn(ProductController.class).getProductHateosById(id)).withSelfRel()
                        .andAffordance(afford(methodOn(ProductController.class).updateProductPut(id, null)))
                        .andAffordance(afford(methodOn(ProductController.class).deleteProduct(id))),
                linkTo(methodOn(ProductController.class).getProducts(0,0, 0, 0)).withRel("products")
                );

        return entityModel;
    }
}
