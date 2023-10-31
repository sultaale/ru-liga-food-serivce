package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForOrderCreationDTO {

    @Min(1)
    @NotNull
    private Integer quantity;
    @JsonProperty("menu_item_id")
    private Long menuItemId;
}
