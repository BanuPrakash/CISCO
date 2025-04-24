package com.cisco.orderapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="products")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required!!!")
    private  String name;

    @Min(value=10, message = "Price ${validatedValue} should be more than {value}")
    private  double price;


    @Column(name="qty")
    @Min(value=1, message = "Quantity ${validatedValue} should be more than {value}")
    private int quantity;

    @Version
    private int ver;

}
