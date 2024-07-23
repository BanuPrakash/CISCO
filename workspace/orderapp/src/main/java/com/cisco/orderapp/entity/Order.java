package com.cisco.orderapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="order_date")
    private Date orderDate = new Date(); // system date

    @ManyToOne
    @JoinColumn(name="customer_fk") // FK
    private Customer customer; // order is by a given Customer


    @OneToMany
    @JoinColumn(name="order_fk")
    private List<LineItem> items = new ArrayList<>();

    private double total;
}
