package ru.liga.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.liga.exceptions.RestaurantMenuException;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(ru.liga.service.Impl.RestaurantMenuServiceImpl)")
    public void stringProcessingMethods() {};

    @Before("stringProcessingMethods()")
    public void beforeAdvice(JoinPoint jp) {
        logger.log(Level.INFO, "Выполняется метод " + jp.getSignature().getName());
    }

    @AfterThrowing(pointcut = "stringProcessingMethods()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint jp, RestaurantMenuException ex) {
       String methodName = jp.getSignature().getName();
       logger.log(Level.WARNING, "Меню не найдено, проверьте id", ex.getMessage());
    }
}
