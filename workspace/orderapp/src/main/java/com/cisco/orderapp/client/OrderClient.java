package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
        newOrder();
    }

    private void newOrder() {
        Customer customer = new Customer();
        customer.setEmail("roger@cisco.com");

        Order order = new Order();
        order.setCustomer(customer); // order is by customer

        LineItem item1 = new LineItem();
        Product p1 = new Product();
        p1.setId(3);
        item1.setProduct(p1);
        item1.setQty(2);

        LineItem item2 = new LineItem();
        Product p2 = new Product();
        p2.setId(1);
        item2.setProduct(p2);
        item2.setQty(3);

        order.getItems().add(item1); // items of order
        order.getItems().add(item2);

        service.placeOrder(order);
    }
}
