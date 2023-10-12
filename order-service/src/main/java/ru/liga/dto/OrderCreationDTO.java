package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderCreationDTO {
    private Long restaurantId;
    @JsonProperty(namespace = "menu_items")
    private List<OrderItemForOrderCreationDTO> orderItems;
}