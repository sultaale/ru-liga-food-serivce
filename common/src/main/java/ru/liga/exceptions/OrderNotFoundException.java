package ru.liga.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}