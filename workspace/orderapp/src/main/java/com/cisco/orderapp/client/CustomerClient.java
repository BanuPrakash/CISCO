package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerClient implements CommandLineRunner {
    private  final OrderService orderService;

    // execute as soon as spring context is created and initialized
    @Override
    public void run(String... args) throws Exception {
//        insertCustomers();
    }

    private void insertCustomers() {
        if(orderService.getCustomerCount() == 0) {
            orderService.saveCustomer(new Customer("roger@cisco.com", "Roger", "Smith"));
            orderService.saveCustomer(new Customer("anne@cisco.com", "Anne", "Hathaway"));
            orderService.saveCustomer(new Customer("angelina@cisco.com", "Angelina", "Jolie"));
        }
    }
}
