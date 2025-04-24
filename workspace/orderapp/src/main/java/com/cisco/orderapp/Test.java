package com.cisco.orderapp;

import com.cisco.orderapp.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args) throws  Exception{
        Product product = Product.builder().name("A").price(342.22).quantity(100).build();

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(product));
    }
}
