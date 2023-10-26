package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeliveryDTO {
    @JsonProperty("order_action")
    private String status;
}
