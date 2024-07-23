package com.cisco.prj.dao;

import com.cisco.prj.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements  ProductDao{
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL ="jdbc:mysql://localhost:3306/CISCO_JAVA";
    private static String USER = "root";
    private  static  String PASSWORD = "Welcome123";

    static  {
        try {
            Class.forName(DRIVER); // load the drivers
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void addProduct(Product p) throws PersistenceException{
        Connection con = null;
        try {
            String SQL = "INSERT INTO products (id, name, price) VALUES(0, ?, ?)";
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex) {
            if(ex.getErrorCode() ==1062) {
                throw new PersistenceException("Product with given id: " + p.getId() + " already exists!!", ex);
            } else if(ex.getErrorCode() == 1054) {
                throw new PersistenceException("Unable to add Product!!", ex);
            }

        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        String SQL = "SELECT id, name, price FROM products";
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                products.add(Product.builder()
                        .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .price(rs.getDouble("price"))
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
