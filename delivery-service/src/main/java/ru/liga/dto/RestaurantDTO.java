package ru.liga.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RestaurantDTO {
    private String address;
    private int distance;
}
