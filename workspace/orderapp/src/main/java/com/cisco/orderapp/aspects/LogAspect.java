package com.cisco.orderapp.aspects;

import com.cisco.orderapp.service.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
@Slf4j
public class LogAspect {

    // https://docs.spring.io/spring-framework/docs/4.3.14.RELEASE/spring-framework-reference/html/aop.html
    @Before("execution(* com.cisco.orderapp.service.*.*(..))")
    public void logBefore(JoinPoint jp) {
        log.info("Called : "  + jp.getSignature());
        Object[] args = jp.getArgs();
        for(Object arg: args) {
            log.info("Argument : " + arg);
        }
    }

    @After("execution(* com.cisco.orderapp.service.*.*(..))")
    public void logAfter(JoinPoint jp) {
        log.info("******");
    }

    @Around("execution(* com.cisco.orderapp.service.*.*(..))")
    public Object doProfile(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = new Date().getTime();
            Object obj = pjp.proceed(); // invoke the actual method
        long endTime = new Date().getTime();
        log.info("Time taken to execute " + pjp.getSignature().getName() + " is : " + (endTime - startTime) + " ms");
        return obj;
    }

    @Around("execution(* com.cisco.orderapp.api.PostController.*(..))")
    public Object doProfileController(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = new Date().getTime();
        Object obj = pjp.proceed(); // invoke the actual method
        long endTime = new Date().getTime();
        log.info("Time taken to execute " + pjp.getSignature().getName() + " is : " + (endTime - startTime) + " ms");
        return obj;
    }


    @AfterThrowing(value = "execution(* com.cisco.orderapp.service.*.*(..))", throwing = "ex")
    public void handleException(EntityNotFoundException ex) {
        log.info("Exception :" + ex.getMessage());
    }
}
