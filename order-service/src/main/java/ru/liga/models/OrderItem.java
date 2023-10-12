package ru.liga.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long id;
    private Order order;
    private RestaurantMenuItem restaurantMenuItem;
    private Long price;
    private Integer quantity;
}
