package com.cisco.datarest.api;

import com.cisco.datarest.entity.Product;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@BasePathAwareController
public class ProductController {

    @RequestMapping(path = "products", method = RequestMethod.GET)
    public @ResponseBody  List<Product> getProducts() {
        // to add custom Links using WebMvcLinkBuilder
        return Arrays.asList(
          Product.builder().id(100).name("A").price(1423).build(),
                Product.builder().id(54).name("B").price(9888).build()
        );
    }

    @RequestMapping(path = "hello", method = RequestMethod.GET)
    public @ResponseBody String getGreet() {
        return  "Hello Spring Data REST!!!";
    }

}
