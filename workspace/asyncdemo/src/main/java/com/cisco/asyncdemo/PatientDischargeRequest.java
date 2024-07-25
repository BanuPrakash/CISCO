package com.cisco.asyncdemo;

import lombok.Data;

@Data
public class PatientDischargeRequest {
    private String patientId;
    private String patientName;
}
