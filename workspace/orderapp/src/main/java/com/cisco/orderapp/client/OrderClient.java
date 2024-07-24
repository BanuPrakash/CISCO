package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderClient implements CommandLineRunner {
    @Autowired
    OrderService service;

    @Override
    public void run(String... args) throws Exception {
        placeOrder();
        getOrders();
    }

    private  void placeOrder() {
        Order order = new Order(); // system date is set
        order.setCustomer(Customer.builder()
                .email("anna@gmail.com").build());

        List<LineItem> items = new ArrayList<>();
        items.add(LineItem.builder()
                .product(Product.builder().id(2).build())
                .qty(1).
                build());

        items.add(LineItem.builder()
                .product(Product.builder().id(4).build())
                .qty(2).
                build());

        order.setItems(items);
        service.placeOrder(order);
    }
    private void getOrders() {
    }
}
