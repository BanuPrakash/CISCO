package com.cisco.orderapp.dao;

import com.cisco.orderapp.dto.ReportDTO;
import com.cisco.orderapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {
   // @Query(value = "select new com.cisco.orderapp.dto.ReportDTO(c.email, c.fname, c.lname, o.order_date, o.total)  from orders o inner join  customers c on o.customer_fk = c.email", nativeQuery = true)
    @Query("select new com.cisco.orderapp.dto.ReportDTO(c.email, c.firstName, c.lastName, o.orderDate, o.total) from Order o inner join o.customer c")
    List<ReportDTO> getReport();
}
