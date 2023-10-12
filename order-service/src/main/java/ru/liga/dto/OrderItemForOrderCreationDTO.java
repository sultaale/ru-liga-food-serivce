package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderItemForOrderCreationDTO {
    private Integer quantity;
    private Long menuItemId;
}
