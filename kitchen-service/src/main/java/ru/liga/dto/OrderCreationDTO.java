package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class OrderCreationDTO {
   
    private Long id;
    @JsonProperty("menu_items")
    private List<OrderItemForCreationDTO> orderItems;
}