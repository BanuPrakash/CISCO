package com.cisco.orderapp.api;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @GetMapping("/hateoas/{id}")
    public EntityModel<Product> getByIdHateos(@PathVariable("id") int id) throws ResourceNotFoundException {
        Product product = service.getProductById(id);
        Link selfLink = linkTo(methodOn(ProductController.class).getById(id)).withSelfRel();
        Affordance update = afford(methodOn(ProductController.class).updateProduct(id, null));
        Affordance delete = afford(methodOn(ProductController.class).deleteProduct(id));

        Link productsLink = linkTo(methodOn(ProductController.class).getProducts(0,0)).withRel("products");
        EntityModel<Product> model = EntityModel.of(product, selfLink.andAffordance(update),
                selfLink.andAffordance(delete),
                productsLink);
        return  model;
    }

    // GET http://localhost:8080/api/products/3
    @Operation(
            description = "Service that return a Product",
            summary = "This service returns a Product by the ID",
            responses = {
                    @ApiResponse(description = "Successful Operation", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "Product  Not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
            })
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

    public  class StringType {
        String msg;
        public StringType(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }
    @Hidden
    @DeleteMapping("/{id}")
    public StringType deleteProduct(@PathVariable("id") int id) {
        // service.deleteProduct(id);
        return new StringType("delete product!!!");
    }
}
