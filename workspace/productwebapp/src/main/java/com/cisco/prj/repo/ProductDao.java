package com.cisco.prj.repo;

import com.cisco.prj.entity.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product p);
    List<Product> getProducts();
}
