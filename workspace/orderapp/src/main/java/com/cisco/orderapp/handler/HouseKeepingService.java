package com.cisco.orderapp.handler;

import com.cisco.orderapp.events.PatientDischargeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HouseKeepingService {

    @EventListener
    @Async
    public void processHouseKeeping(PatientDischargeEvent event) {
        log.info("Thread " + Thread.currentThread().toString());
        log.info("House Keeping cleaning room occupied by :"  + event.getPatientName());
    }
}
