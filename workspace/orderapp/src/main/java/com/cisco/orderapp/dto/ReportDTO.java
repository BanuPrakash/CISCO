package com.cisco.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportDTO {
    String email;
    String firstName;
    String lastName;
    Date orderDate;
    double total;
}
