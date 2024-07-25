package com.cisco.asyncdemo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BillingServiceHandler {

    @Async
    @EventListener
    public void processBill(PatientDischargeEvent event) {
        System.out.println("Billing Service for " + event.getPatientName() + " executed in Thread " + Thread.currentThread());
    }
}
