package com.OneLab.Practice1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {
        // Pointcut for all @RestController classes
    }

    @Around("controllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // Get request attributes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        // Retrieve request details
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String params = Arrays.toString(joinPoint.getArgs());
        String ipAddress = request.getRemoteAddr();
        String username = getUsername();

        logger.info("Incoming request: method={}, uri={}, params={}, ip={}, username={}", method, uri, params, ipAddress, username);

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        // Log response details
        int status = response != null ? response.getStatus() : 0;
        logger.info("Outgoing response: status={}, response={}", status, result);

        return result;
    }

    private String getUsername() {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }
}

