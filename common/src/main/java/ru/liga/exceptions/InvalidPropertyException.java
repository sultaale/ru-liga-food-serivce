package ru.liga.exceptions;

public class InvalidPropertyException extends RuntimeException{
    public InvalidPropertyException(String msg) {
        super(msg);
    }
}
