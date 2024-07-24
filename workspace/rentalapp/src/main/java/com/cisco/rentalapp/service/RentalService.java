package com.cisco.rentalapp.service;

import com.cisco.rentalapp.dao.CustomerDao;
import com.cisco.rentalapp.dao.RentalDao;
import com.cisco.rentalapp.dao.VehicleDao;
import com.cisco.rentalapp.entity.Customer;
import com.cisco.rentalapp.entity.Rental;
import com.cisco.rentalapp.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final CustomerDao customerDao;
    private final VehicleDao vehicleDao;
    private final RentalDao rentalDao;

    public long getCustomerCount() {
        return customerDao.count();
    }

    public long getVehicleCount() {
        return vehicleDao.count();
    }

    public Customer addCustomer(Customer c) {
        return customerDao.save(c);
    }

    public Vehicle addVehicle(Vehicle v) {
        return vehicleDao.save(v);
    }

    public Rental rental(Rental r) {
        return rentalDao.save(r);
    }

    public List<Rental> getRentals() {
        return rentalDao.findAll();
    }
}
