package com.cisco.rentalapp.client;

import com.cisco.rentalapp.entity.Vehicle;
import com.cisco.rentalapp.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class VehicleClient implements CommandLineRunner {
    @Autowired
    RentalService service;

    @Override
    public void run(String... args) throws Exception {
        addVehicles();
    }

    private void addVehicles() {
        if(service.getVehicleCount() == 0) {
            service.addVehicle(Vehicle.builder()
                    .registrationNumber("KA01AE1321")
                    .fuelType("ELECTRIC")
                    .costPerDay(3500).build());
            service.addVehicle(Vehicle.builder()
                    .registrationNumber("UP11ER5112")
                    .fuelType("PETROL")
                    .costPerDay(2500).build());
            service.addVehicle(Vehicle.builder()
                    .registrationNumber("DH12E6444")
                    .fuelType("DIESEL")
                    .costPerDay(4500).build());
        }
    }
}
