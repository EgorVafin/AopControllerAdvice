package com.example.aop_controlleradvice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Pointcut("within(com.example.aop_controlleradvice.controller.*)")
    public void isControllerLayer() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void hasRequestMapping() {}

    @Pointcut("target(org.springframework.web.bind.annotation.RestController)")
    public void isRequestController() {}

    @Pointcut("@target(org.springframework.web.bind.annotation.ControllerAdvice)")
    public void isControllerAdvice() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void isExceptionHandler() {}


    @Around("isControllerAdvice() && isExceptionHandler()")
    public Object addLoggingAroundToExceptionHandler(ProceedingJoinPoint joinPoint) throws Throwable{

        logger.info("Around aspect. Exception handler started. Signature: " + joinPoint.getSignature());
        long startTime = System.nanoTime();

        Object proceed = joinPoint.proceed();

        long endTime = System.nanoTime();
        logger.info("Around aspect. Exception handler finished. It is takes " + (endTime - startTime)/1000 + " ms");

        return proceed;
    }


    @Around("isControllerLayer() && hasRequestMapping()")
    public Object addLoggingAroundToController(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("Around aspect. RequestMapping method started. Signature: " + joinPoint.getSignature());
        long startTime = System.nanoTime();

        Object proceed = joinPoint.proceed();

        long endTime = System.nanoTime();
        logger.info("Around aspect. RequestMapping method finished. It is takes " + (endTime - startTime)/1000 + " ms");

        return proceed;
    }

//    @Before("isControllerLayer() && hasRequestMapping()")
//    public void addLoggingBefore() throws Throwable {
//        logger.info("RequestMapping method started");
//    }
//
//    @After("isControllerLayer() && hasRequestMapping()")
//    public void addLoggingAfter() throws Throwable{
//        logger.info("RequestMapping method finished");
//    }
}
