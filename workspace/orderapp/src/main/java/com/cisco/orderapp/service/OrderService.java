package com.cisco.orderapp.service;

import com.cisco.orderapp.dao.CustomerDao;
import com.cisco.orderapp.dao.ProductDao;
import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private CustomerDao customerDao;
    
//    // Spring Data JPA generates classes for the JpaRepository interface
//    // which is wired
//    private final ProductDao productDao; // Constructor DI instead of @Autowired
//    private final CustomerDao customerDao;  // Constructor DI

    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public Product saveProduct(Product p) {
        return productDao.save(p);
    }

    public void deleteProduct(int id) {
        productDao.deleteById(id);
    }

    public Customer saveCustomer(Customer c) {
        return customerDao.save(c);
    }

    public List<Customer> getCustomers() {
        return customerDao.findAll();
    }
}
