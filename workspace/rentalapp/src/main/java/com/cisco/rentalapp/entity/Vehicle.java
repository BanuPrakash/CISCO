package com.cisco.rentalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Column(name="REG_NO")
    private String registrationNumber;

    @Column(name="FUEL_TYPE")
    private String fuelType;

    @Column(name = "COST_PER_DAY")
    private double costPerDay;
}
