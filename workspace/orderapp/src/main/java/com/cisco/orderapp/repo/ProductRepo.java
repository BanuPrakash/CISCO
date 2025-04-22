package com.cisco.orderapp.repo;

import com.cisco.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    // select * from products where price = ?
    List<Product> findByPrice(double price);

    // select * from products where qty = ?
    List<Product> findByQuantity(int quantity);

    // select * from products where name like %?%
    List<Product> findByNameLike(String name);

    // select * from products where price >= low and price <= high
    List<Product> findByPriceBetween(double low, double high);

    List<Product> findByNameAndPrice(String name, double price);

    // JP-QL
    @Query("from Product where price >= :l and price <= :h")
    // SQL
//    @Query(value = "select * from products where price >= :l and price <= :h", nativeQuery = true)
    List<Product> findByRange(@Param("l") double low, @Param("h") double high);

    @Modifying  // for INSERT, DELETE and UPDATE
    @Query("update Product set price = :pr where id = :id")
    void updatePrice(@Param("id") int id, @Param("pr") double price);
}
