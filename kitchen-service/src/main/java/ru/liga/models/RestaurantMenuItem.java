package ru.liga.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantMenuItem {
    private Long id;
    private Restaurant restaurant;
    private String name;
    private Long price;
    private String image;
    private String description;
}
