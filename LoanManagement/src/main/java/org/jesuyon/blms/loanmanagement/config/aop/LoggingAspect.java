package org.jesuyon.blms.loanmanagement.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* org.jesuyon.blms.loanmanagement.service.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Executing method: {}", methodName);
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                logger.info("Argument: {}", arg);
            }
        } else {
            logger.info("No arguments passed to method: {}", methodName);
        }
    }

    @AfterReturning(pointcut = "execution(* org.jesuyon.blms.loanmanagement.service.*.*(..))", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method executed successfully: {}", methodName);
        if (result != null) {
            logger.info("Return value: {}", result);
        } else {
            logger.info("Method returned void or null: {}", methodName);
        }
    }

    @AfterThrowing(pointcut = "execution(* org.jesuyon.blms.loanmanagement.service.*.*(..))", throwing = "exception")
    public void logExceptions(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception thrown in method: {}", methodName);
        logger.error("Exception message: {}", exception.getMessage());
    }
}