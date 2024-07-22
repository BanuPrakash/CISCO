package com.cisco.prj.dao;

import com.cisco.prj.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void addProduct(Product p) throws PersistenceException;
    List<Product> getProducts();
    Product getProductById(int id);
    void deleteProduct(int id);
}
