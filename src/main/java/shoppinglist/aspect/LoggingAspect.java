package shoppinglist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger("Aspect logger");

    private static final String ALL_METHODS_LOGGABLE = "execution(public * *(..)) " +
            "&& within(@shoppinglist.annotation.LoggableTimeSpentOnMethods *)";

    private LocalTime start;

    @Pointcut(ALL_METHODS_LOGGABLE)
    public void callMethod() {
    }

    @Before("callMethod()")
    public void beforeCallMethod(JoinPoint joinPoint) {
        start = LocalTime.now();
        logger.info("Method: " + getMethodSignature(joinPoint) +
                " starts with args=[" + getMethodArgs(joinPoint) + "]");
    }

    @After("callMethod()")
    public void afterCallMethod(JoinPoint joinPoint) {
        LocalTime finish = LocalTime.now();
        long timeSpent = start.until(finish, ChronoUnit.MILLIS);
        logger.info("Time spent method " + joinPoint.getSignature().getName() + ": " + timeSpent + " mcs");
    }

    private String getMethodArgs(JoinPoint joinPoint) {
        return Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    private String getMethodSignature(JoinPoint joinPoint) {
        return joinPoint.getSignature().toString();
    }
}