package com.cisco.orderapp.repo;

import com.cisco.orderapp.dto.ReportDTO;
import com.cisco.orderapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {

//    @Query(value = "select c.fname, c.email, o.order_date, o.total  " +
//            "from orders o " +
//            "inner join customers c on c.email = o.customer_fk", nativeQuery = true)
//    List<Object[]> getReport(); // each row has many objects object[0] --> fname, object[1] --> email

//
//    @Query("select c.firstName, c.email, o.orderDate, o.total from Order o inner join o.customer c")
//    List<Object[]> getReport();


    @Query("select new com.cisco.orderapp.dto.ReportDTO(c.firstName, c.email, o.orderDate, o.total) from Order o inner join o.customer c")
    List<ReportDTO> getReport();
}
