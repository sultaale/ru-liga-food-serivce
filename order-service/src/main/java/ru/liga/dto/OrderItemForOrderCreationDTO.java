package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderItemForOrderCreationDTO {
    private Integer quantity;
    @JsonProperty("menu_item_id")
    private Long menuItemId;
}
