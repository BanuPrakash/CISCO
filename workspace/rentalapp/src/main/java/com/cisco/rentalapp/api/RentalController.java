package com.cisco.rentalapp.api;

import com.cisco.rentalapp.entity.Rental;
import com.cisco.rentalapp.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rentals")
public class RentalController {
    @Autowired
    RentalService service;

    @PostMapping
    public String placeRental(@RequestBody @Valid  Rental rental) {
        service.rental(rental);
        return  "Rental placed!!!";
    }
}
