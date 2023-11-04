package ru.liga.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super("Restaurant not found by id " + id);
    }

    public RestaurantNotFoundException(String msg) {
        super(msg);
    }
}
