package com.cisco.rentalapp.dao;

import com.cisco.rentalapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, String> {
}
