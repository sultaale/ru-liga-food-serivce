package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDTO {
    @JsonProperty("order_id")
    private Long id;
    @JsonProperty("restaurant")
    private RestaurantDTO restaurantDTO;
    @JsonProperty("customer")
    private CustomerDTO customerDTO;
    private Double payment;
}
