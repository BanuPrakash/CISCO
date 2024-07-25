package com.cisco.asyncdemo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HouseKeepingServiceHandler {

    @Async
    @EventListener
    public void cleanAndAssign(PatientDischargeEvent event) {
        System.out.println("Housing Keeping Service for " + event.getPatientName() + " executed in Thread " + Thread.currentThread());
    }
}
