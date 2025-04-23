package com.cisco.orderapp.dto;

import java.util.Date;

// immutable --> constructor + getters
public record ReportDTO(String firstName, String email, Date orderDate, double total) {
}
