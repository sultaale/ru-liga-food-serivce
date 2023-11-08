package ru.liga.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(UUID id) {
        super("Could not find order " + id);
    }

    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
