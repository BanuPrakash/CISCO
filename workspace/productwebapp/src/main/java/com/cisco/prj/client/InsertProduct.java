package com.cisco.prj.client;

import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;

public class InsertProduct {
    public static void main(String[] args) {
        Product p = Product.builder().
                name("Wacom").
                price(4500.00).
                build();
        ProductDao productDao = new ProductDaoJdbcImpl();
        productDao.addProduct(p);
        System.out.println("Product added!!!");
    }
}
