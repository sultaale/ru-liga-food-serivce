package ru.liga.exceptions;

public class CourierNotFoundException extends RuntimeException {
     public CourierNotFoundException(String msg) {
         super(msg);
     }
}
