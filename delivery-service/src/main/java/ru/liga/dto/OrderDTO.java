package ru.liga.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID id;
    private RestaurantDTO restaurant;
    private LocalDateTime timestamp;
    private List<OrderItemDTO> items;

}
