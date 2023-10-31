package ru.liga.exceptions;

public class RestaurantMenuException extends RuntimeException {
    public RestaurantMenuException(String msg) {
        super(msg);
    }

    public RestaurantMenuException(Long id) {
        super("Menu not found by id " + id);
    }
}
