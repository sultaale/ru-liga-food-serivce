package ru.liga.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Double price;
    private Integer quantity;
    private String description;
    private String image;
}
