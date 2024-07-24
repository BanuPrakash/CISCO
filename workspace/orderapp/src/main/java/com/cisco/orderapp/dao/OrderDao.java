package com.cisco.orderapp.dao;

import com.cisco.orderapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
