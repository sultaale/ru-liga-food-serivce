package ru.liga.exceptions;

public class StatusIsNotValidException extends RuntimeException{
    public StatusIsNotValidException(String msg) {
        super(msg);
    }
}
