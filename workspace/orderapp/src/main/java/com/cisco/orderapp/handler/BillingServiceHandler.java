package com.cisco.orderapp.handler;

import com.cisco.orderapp.events.PatientDischargeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BillingServiceHandler {

    @EventListener
    @Async
    public void processBill(PatientDischargeEvent event) {
        log.info(Thread.currentThread().toString());
        log.info("Bill Process..."  + event.getPatientName());
    }
}
