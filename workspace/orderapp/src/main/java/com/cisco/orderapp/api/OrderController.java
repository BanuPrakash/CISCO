package com.cisco.orderapp.api;

import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping
    public List<Order> getOrders() {
        return  service.getOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Order o) {
        service.placeOrder(o);
        return "Order Placed!!";
    }
}
