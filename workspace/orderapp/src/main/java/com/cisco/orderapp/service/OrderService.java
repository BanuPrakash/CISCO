package com.cisco.orderapp.service;

import com.cisco.orderapp.dao.CustomerDao;
import com.cisco.orderapp.dao.OrderDao;
import com.cisco.orderapp.dao.ProductDao;
import com.cisco.orderapp.dto.ReportDTO;
import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import jakarta.transaction.Transactional;
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

    @Autowired
    private OrderDao orderDao;

    public List<ReportDTO> getReport() {
        return orderDao.getReport();
    }
    /*
        items amount is computed
        order total is computed
        order_date is system date
        XML
        <order>
            <customer>
                <email>anna@gmail.com</email>
            </customer>
            <items>
            <item>
                <product id="2" />
                <qty>1</qty>
             </item>
            </items>
        </order>
        JSON
        {
            "customer": {"email": "anna@gmail.com"},
            items: [
                { "product": {id: 2}, qty: 1},
                { "product": {id: 4}, qty: 2}
            ]
        }
     */
    @Transactional // Atomic in nature
    public Order placeOrder(Order order) {
        double total = 0.0;
        for(LineItem item : order.getItems()) {
            Product p = productDao.findById(item.getProduct().getId()).get(); //2 | Samsung OLED   | 210000 |  100
            if(p.getQuantity() < item.getQty()) {
                throw  new IllegalArgumentException("Product " + p.getName() + " not in Stock!!!");
            }
            item.setAmount(p.getPrice() * item.getQty()); // minus discount
            total += item.getAmount();
            p.setQuantity(p.getQuantity() - item.getQty()); // no need for explicit UPDATE SQL
            // ORM takes care of UPDATING the entity if it becomes DIRTY ==> DIRTY CHECKING
        }
        order.setTotal(total);
        return orderDao.save(order); // saves order and line_items [CASCADE]
    }

    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    public  List<Product> byRange(double low, double high) {
        return productDao.findByPriceBetween(low, high);
    }

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

    public long getCustomersCount() {
        return customerDao.count();
    }
}
