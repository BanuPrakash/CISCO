package com.cisco.orderapp.service;

import com.cisco.orderapp.events.PatientDischargeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientDischargeService {
    @Autowired
    ApplicationEventPublisher eventPublisher;

    // loosely coupled code
    public String discharge(String patientId, String name) {
        log.info("Patient discharge started!!!" + name);
        eventPublisher.publishEvent(new PatientDischargeEvent(this, patientId, name));
        log.info("Patient discharge done!!!");
        return  "Patient " + name + "  discharge done!!!";
    }

}
