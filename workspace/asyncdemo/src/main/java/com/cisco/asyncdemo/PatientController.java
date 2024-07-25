package com.cisco.asyncdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/discharge")
public class PatientController {
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @PostMapping()
    public String dischargePatient(@RequestBody PatientDischargeRequest request) {
        eventPublisher.publishEvent(new PatientDischargeEvent(this, request.getPatientId(), request.getPatientName()));
        return "Patient discharged ...";
    }
}
