package ru.liga.dto;

import lombok.Builder;
import lombok.Data;
import ru.liga.models.Restaurant;

@Builder
@Data
public class RestaurantMenuDTO {
    private Long id;
    private String name;
    private Double price;
    private String image;
    private String description;
}
