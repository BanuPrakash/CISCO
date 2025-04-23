package com.cisco.orderapp.client;

import com.cisco.orderapp.dto.ReportDTO;
import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.EntityNotFoundException;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
       // newOrder();
//        printOrders();
//        printReport();
    }

    private void printReport() {
        List<ReportDTO> reportDTOS = service.getReport();
        for(ReportDTO report : reportDTOS) {
            System.out.println(report.firstName() +" ," + report.email() + ", " + report.orderDate() + ", " + report.total());
        }
    }

    private void printOrders() {
        List<Order> orders = service.getOrders();
        for(Order o : orders) {
            System.out.println(o.getOid() + ", " + o.getOrderDate() + ", " + o.getTotal());
            System.out.println("Ordered By : " + o.getCustomer().getFirstName()); // ManyToOne
            List<LineItem> items = o.getItems(); // oneToMany
            // o.getItems(); returns a Proxy collection when Lazy loaded
            for(LineItem item: items) {
                System.out.println(item.getProduct().getName() + ", " + item.getQty() + ", " + item.getAmount());
            }
            System.out.println("*********");
        }
    }

    private void newOrder() throws EntityNotFoundException  {
        Customer customer = new Customer();
        customer.setEmail("angelina@cisco.com");

        Order order = new Order();
        order.setCustomer(customer); // order is by customer

        LineItem item1 = new LineItem();
        Product p1 = new Product();
        p1.setId(2);
        item1.setProduct(p1);
        item1.setQty(1);

        LineItem item2 = new LineItem();
        Product p2 = new Product();
        p2.setId(1);
        item2.setProduct(p2);
        item2.setQty(4);

        LineItem item3 = new LineItem();
        Product p3 = new Product();
        p3.setId(3);
        item3.setProduct(p3);
        item3.setQty(2);

        order.getItems().add(item1); // items of order
        order.getItems().add(item2);
        order.getItems().add(item3);

        service.placeOrder(order);
    }
}
