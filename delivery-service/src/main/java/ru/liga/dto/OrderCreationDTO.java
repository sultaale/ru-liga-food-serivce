package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderCreationDTO {
    @JsonProperty("restaurant_id")
    private Long restaurantId;
    @JsonProperty("menu_items")
    private OrderItemForOrderCreationDTO menuItems;
}
