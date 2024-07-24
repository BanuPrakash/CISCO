package com.cisco.orderapp.client;

import com.cisco.orderapp.dto.ReportDTO;
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
     //   placeOrder();
      //  getOrders();
        getReport();
    }

    private void getReport() {
        for(ReportDTO report: service.getReport()) {
            System.out.println(report.getOrderDate()+ "," + report.getFirstName());
        }
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
        List<Order> orders = service.getOrders();
        for(Order o : orders) {
            System.out.println(o.getCustomer().getFirstName() +", " + o.getTotal()); // Anna, 220800.0
            List<LineItem> items = o.getItems();
            for(LineItem item : items) {
                System.out.println(item.getProduct().getName() +", " + item.getQty() + ", " + item.getAmount());
            }
        }
    }
}
