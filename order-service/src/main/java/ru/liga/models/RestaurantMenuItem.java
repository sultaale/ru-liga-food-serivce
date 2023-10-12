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
    private String image; //TODO уточнить как хранится картинка, пока это путь к картинке
    private String description;
}
