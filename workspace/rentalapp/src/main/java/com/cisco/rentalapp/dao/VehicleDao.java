package com.cisco.rentalapp.dao;

import com.cisco.rentalapp.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<Vehicle, String> {
}
