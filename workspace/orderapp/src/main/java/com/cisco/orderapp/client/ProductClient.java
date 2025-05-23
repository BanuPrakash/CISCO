package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.EntityNotFoundException;
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
//        printProducts();
//        getProductById();
//        System.out.println("Get Products By Range!!!");
//        printByRange();

//        System.out.println("Update the product price");
//        updatePrice();

//        System.out.println("Update the product qty");
//        updateQty();
    }

    private void updateQty() throws EntityNotFoundException {
        Product p = orderService.updateProductQty(3); // buy one LG AC
        System.out.println(p);
    }

    private void updatePrice() throws EntityNotFoundException {
        Product p = orderService.updateProduct(2, 8900.50);
        System.out.println(p);
    }

    private void printByRange() {
        List<Product> products = orderService.getByRange(6000, 60_000);
        for(Product p : products) {
            System.out.println(p);
        }
    }

    private void getProductById() throws EntityNotFoundException {
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
