package com.cisco.prj.dao;

import com.cisco.prj.entity.Product;

import java.util.List;

public class ProductDaoJdbcImpl implements  ProductDao{
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL ="jdbc:mysql://localhost:3306/CISCO_JAVA";
    private static String USER = "root";
    private  static  String PASSWORD = "Welcome123";
    
    @Override
    public void addProduct(Product p) {

    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
