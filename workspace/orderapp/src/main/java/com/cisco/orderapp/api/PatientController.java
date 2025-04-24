package com.cisco.orderapp.api;

import com.cisco.orderapp.dto.PatientDischargeRequest;
import com.cisco.orderapp.service.PatientDischargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientDischargeService service;

    @PostMapping("/discharge")
    public String doDischarge(@RequestBody PatientDischargeRequest req) {
        return service.discharge(req.getPatientId(), req.getPatientName());
    }
}
