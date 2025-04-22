package com.cisco.orderapp.service;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.repo.CustomerRepo;
import com.cisco.orderapp.repo.OrderRepo;
import com.cisco.orderapp.repo.ProductRepo;
import jakarta.transaction.Transactional;
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
    private final OrderRepo orderRepo;

    /*
        {
            customer: { "email": "roger@cisco.com"},
            items: [
                { product : {id: 3}, qty: 1 },
                { product : {id: 1}, qty: 2 }
            ]
        }
     */
    // Atomic operation, everything commits
    // any exception rollback entire thing
    // Transactional is also used here for DIRTY CHECKING
    @Transactional
    public String placeOrder(Order order) {
        double total = 0;
        List<LineItem> items = order.getItems();
        for(LineItem item: items) {
            Product p = getProductById(item.getProduct().getId());
            if(item.getQty() > p.getQuantity()) {
                throw  new IllegalArgumentException("Product " + p.getName() + " not is Stock!!!");
            }
            item.setAmount(p.getPrice() * item.getQty()); // + GST - DISCOuNT
            p.setQuantity(p.getQuantity() - item.getQty()); // DIRTY CHECKING
            total += item.getAmount();
        }
        order.setTotal(total);
        orderRepo.save(order); // save order and its line items [cascade]
        return  "Order placed!!!";
    }


    @Transactional
    public Product updateProductQty(int id) {
        Product p = getProductById(id); // fetch from database
        p.setQuantity(p.getQuantity() - 1); // product became dirty --> UPDATE SQL is issued by ORM
        return  p;
    }

    @Transactional
    public Product updateProduct(int id, double price) {
        productRepo.updatePrice(id, price);
        return  getProductById(id);
    }

    public  List<Product> getByRange(double low, double high) {
        return productRepo.findByRange(low, high);
    }

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
