package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CustomerClient implements CommandLineRunner {
    @Autowired
    private OrderService service;

    @Override
    public void run(String... args) throws Exception {
        addCustomers();
    }

    private void addCustomers() {
        if(service.getCustomersCount() == 0) {
            Customer c1 = Customer.builder()
                    .email("roger@gmail.com")
                    .firstName("Roger")
                    .lastName("Smith")
                    .build();
            Customer c2 = Customer.builder()
                    .email("anna@gmail.com")
                    .firstName("Anna")
                    .lastName("Ben")
                    .build();

            service.saveCustomer(c1);
            service.saveCustomer(c2);
        }
    }
}
