package com.cisco.orderapp.dao;

import com.cisco.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}

