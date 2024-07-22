package com.cisco.prj.client;

import com.cisco.prj.dao.ProductDao;
import com.cisco.prj.dao.ProductDaoJdbcImpl;
import com.cisco.prj.entity.Product;

import java.util.List;

public class ListProducts {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        for(Product p : products) {
            System.out.println(p); // toString() by lombok
        }
    }
}
