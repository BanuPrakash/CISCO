package com.cisco.orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDischargeRequest {
    private String patientId;
    private String patientName;
}
