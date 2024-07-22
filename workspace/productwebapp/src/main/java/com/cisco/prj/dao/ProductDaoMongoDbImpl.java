package com.cisco.prj.dao;

import com.cisco.prj.entity.Product;

import java.util.List;

public class ProductDaoMongoDbImpl implements   ProductDao{
    @Override
    public void addProduct(Product p) throws PersistenceException {
//        try {
//
//        } catch (MongoException ex) {
//            throw new PersistenceException("unable to add product!!!");
//        }
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
