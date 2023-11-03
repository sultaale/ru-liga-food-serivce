package ru.liga.dto;

import lombok.Data;


@Data
public class MenuCreationDTO {
    private Long restaurantId;
    private String name;
    private Double price;
    private String image;
    private String description;
}
