package ru.liga.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg) {
        super(msg);
    }

    public CustomerNotFoundException(Long id) {
        super("Could not find customer by id " + id);
    }
}
