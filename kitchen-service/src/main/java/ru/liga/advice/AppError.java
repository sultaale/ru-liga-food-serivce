package ru.liga.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private int statusCode;
    private String message;
    private LocalDateTime time;

    public AppError(int statusCode){
        this.statusCode = statusCode;
    }
}
