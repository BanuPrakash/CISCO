package com.cisco.rentalapp.client;


import com.cisco.rentalapp.entity.Customer;
import com.cisco.rentalapp.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CustomerClient implements CommandLineRunner {
    @Autowired
    private RentalService service;

    @Override
    public void run(String... args) throws Exception {
        addCustomers();
    }

    private void addCustomers() {
        if(service.getCustomerCount() == 0) {
            Customer c1 = Customer.builder()
                    .email("harry@gmail.com")
                    .firstName("Harry")
                    .lastName("Potter")
                    .build();
            Customer c2 = Customer.builder()
                    .email("veronica@gmail.com")
                    .firstName("Veronica")
                    .lastName("Brad")
                    .build();

            service.addCustomer(c1);
            service.addCustomer(c2);
        }
    }
}
