package com.cisco.prj.client;

import com.cisco.prj.dao.PersistenceException;
import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;

import java.sql.SQLException;

public class InsertProduct {
    public static void main(String[] args) {
        Product p = Product.builder().
                name("Wacom").
                price(4500.00).
                build();

//       Product p = new Product(0, "Wacom", 4500.00);
        ProductDao productDao = new ProductDaoJdbcImpl();
        try {
            productDao.addProduct(p);
            System.out.println("Product added!!!");
        } catch (PersistenceException e) {
            e.printStackTrace();
    //        System.out.println(e.getMessage());
        }
    }
}
