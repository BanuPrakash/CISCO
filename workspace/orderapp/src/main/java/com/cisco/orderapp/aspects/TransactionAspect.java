package com.cisco.orderapp.aspects;

import jakarta.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TransactionAspect {

    @Around("@annotation(Tx)")
    public Object doTransaction(ProceedingJoinPoint pjp) throws  Throwable {
        Object ret = null;
        try {
            log.info("Begin Transaction ...");
//            Transaction tx =
                ret = pjp.proceed(); // invoke the actual method
            //tx.commit();
            log.info("End Transaction...");
        } catch (Exception ex) {
//            tx.rollback();
            log.info("Boom :-(" + ex.getMessage());
        }
        return  ret;
    }
}
