package ru.liga.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.liga.exceptions.OrderNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class KitchenControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    public AppError catchOrderNotFoundException(OrderNotFoundException e) {
        return new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    }
}
