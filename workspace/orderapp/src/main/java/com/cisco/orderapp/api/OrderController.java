package com.cisco.orderapp.api;

import com.cisco.orderapp.dto.ReportDTO;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    // GET http://localhost:8080/api/orders
    // GET http://localhost:8080/api/orders?orderDate=2025-04-23

    @GetMapping()
    public List<Order> getOrders(@RequestParam(value = "orderDate", required = false)
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        if(date == null) {
            return orderService.getOrders();
        }
        return  orderService.byDate(date);
    }

    @GetMapping("/report")
    public List<ReportDTO> getReport() {
        return  orderService.getReport();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
