package ru.liga.exceptions;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(String msg) {
        super(msg);
    }
}
