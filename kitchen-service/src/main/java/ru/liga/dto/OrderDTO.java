package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.liga.models.OrderItem;

@Data
public class OrderDTO {
    private Long id;
    @JsonProperty("manu_items")
    private OrderItemDTO orderItem;
}
