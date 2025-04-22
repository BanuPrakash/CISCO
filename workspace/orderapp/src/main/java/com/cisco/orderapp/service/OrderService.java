package com.cisco.orderapp.service;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.repo.CustomerRepo;
import com.cisco.orderapp.repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// services are facade over Repositories and business code
// services are generally transactional
@Service
@AllArgsConstructor
public class OrderService {
    // prefer constructor DI this instead of @Autowired
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    public List<Product> getProducts() {
        return  productRepo.findAll();
    }

    public Product getProductById(int id) {
        Optional<Product> opt =  productRepo.findById(id);
        if(opt.isPresent()) {
            return  opt.get();
        }
        throw  new RuntimeException("Product with id " + id + " doesn't exist!!");
    }

    public Product saveProduct(Product p) {
        return  productRepo.save(p);
    }

    public Customer saveCustomer(Customer c) {
        return  customerRepo.save(c);
    }

    public long getCustomerCount() {
        return  customerRepo.count();
    }

    public List<Customer> getCustomers() {
        return  customerRepo.findAll();
    }
}
