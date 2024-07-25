package com.cisco.datarest.dao;


import com.cisco.datarest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    //select * from products where qty = ?;
    // quantity  is mapped to qty
    List<Product> findByQuantity(int qty);

    // select * from products where price >= ? and price <= ?
    List<Product> findByPriceBetween(double low, double high);

    //select * from products where name IN ("iPhone 15", "Wacom");
    List<Product> findByNameIn(String[] names);

    //select * from products where name like '%i%';
    List<Product> findByNameLike(String name);
}