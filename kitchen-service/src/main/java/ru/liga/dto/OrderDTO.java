package ru.liga.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderDTO {
    private Long id;
    private RestaurantDTO restaurant;
    private LocalDateTime timestamp;
    private List<OrderItemDTO> items;
}
