package com.cisco.rentalapp.dao;

import com.cisco.rentalapp.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDao extends JpaRepository<Rental, Integer> {
}
