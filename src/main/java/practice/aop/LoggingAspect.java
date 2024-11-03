package practice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
    @Before("execution(* practice.repository.*.*(..))")
    public void beforeRepositoryMethod() {
        logger.info("Before executing repository method");
    }

    // After advice
    @After("execution(* practice.repository.*.*(..))")
    public void afterRepositoryMethod() {
        logger.info("After executing repository method");
    }

    // AfterReturning advice
    @AfterReturning(pointcut = "execution(* practice.repository.*.*(..))", returning = "result")
    public void afterReturningRepositoryMethod(Object result) {
        logger.info("Repository method returned: {}", result);
    }

    // AfterThrowing advice
    @AfterThrowing(pointcut = "execution(* practice.repository.*.*(..))", throwing = "ex")
    public void afterThrowingRepositoryMethod(Exception ex) {
        logger.info("An exception has been thrown: {}", ex.getMessage());
    }

    // Define a dedicated exception
    public static class RepositoryMethodException extends Exception {
        public RepositoryMethodException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Around advice
    @Around("execution(* practice.repository.*.*(..))")
    public Object aroundRepositoryMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Around advice: Before method execution");

        long startTime = System.currentTimeMillis();

        Object result;

        try {
            result = joinPoint.proceed(); // Proceed to the next advice or target method
        } catch (Exception ex) {
            throw new RepositoryMethodException("Exception in around advice for method: " + joinPoint.getSignature(), ex);
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Around advice: After method execution. Time taken: {} ms", timeTaken);

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
            if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }
}
