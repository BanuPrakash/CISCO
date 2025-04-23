package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestClient implements CommandLineRunner {
    private final RestTemplate template;
    record ProductPOJO(int id, String title, double price, String image) {}
    @Override
    public void run(String... args) throws Exception {
        getProductsFromApi();
        getProductById();
        addProductUsingRestTemplate();
    }

    private void addProductUsingRestTemplate() {
        Product product = Product.builder().name("VIVO").price(25000.00).build();
        ResponseEntity<Product> response =
                template.postForEntity("http://localhost:8080/api/products", product, Product.class);
        System.out.println(response.getStatusCode()); // 201
    }

    private void getProductById() {
       ResponseEntity<ProductPOJO> responseEntity =
               template.getForEntity("https://fakestoreapi.com/products/1", ProductPOJO.class);

        System.out.println(responseEntity.getStatusCode()); // 200
        System.out.println(responseEntity.getBody());
    }

    private void getProductsFromApi() {
        String result = template.getForObject("https://fakestoreapi.com/products", String.class);
        System.out.println(result);
    }
}
