package com.OneLab.Practice1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Before advice
    @Before("execution(* com.OneLab.Practice1.repository.*.*(..))")
    public void beforeRepositoryMethod() {
        System.out.println("Before executing repository method");
    }

    // After advice
    @After("execution(* com.OneLab.Practice1.repository.*.*(..))")
    public void afterRepositoryMethod() {
        System.out.println("After executing repository method");
    }

    // AfterReturning advice
    @AfterReturning(pointcut = "execution(* com.OneLab.Practice1.repository.*.*(..))", returning = "result")
    public void afterReturningRepositoryMethod(Object result) {
        System.out.println("Repository method returned: " + result);
    }

    // AfterThrowing advice
    @AfterThrowing(pointcut = "execution(* com.OneLab.Practice1.repository.*.*(..))", throwing = "ex")
    public void afterThrowingRepositoryMethod(Exception ex) {
        System.out.println("An exception has been thrown: " + ex.getMessage());
    }

    // Around advice
    @Around("execution(* com.OneLab.Practice1.repository.*.*(..))")
    public Object aroundRepositoryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around advice: Before method execution");

        long startTime = System.currentTimeMillis();

        Object result;

        try {
            result = joinPoint.proceed(); // Proceed to the next advice or target method
        } catch (Exception ex) {
            System.out.println("Around advice: Exception caught: " + ex.getMessage());
            throw ex;
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Around advice: After method execution. Time taken: " + timeTaken + " ms");

        return result;
    }
}

