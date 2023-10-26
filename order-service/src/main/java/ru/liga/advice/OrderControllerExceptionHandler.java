package ru.liga.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.liga.exceptions.CustomerNotFoundException;

import java.time.LocalDateTime;
import java.util.InvalidPropertiesFormatException;

@ControllerAdvice
public class OrderControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    public AppError catchCustomerNotFoundException(CustomerNotFoundException e) {
        return new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    }

    @ResponseBody
    @ExceptionHandler({InvalidPropertiesFormatException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public AppError catchInvalidPropertiesException(InvalidPropertiesFormatException e) {
        return new AppError(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now());
    }

}
