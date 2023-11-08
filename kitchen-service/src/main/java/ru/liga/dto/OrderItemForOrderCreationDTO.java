package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Builder
@Data
public class OrderItemForOrderCreationDTO {
    @Min(1)
    @NotNull
    private Integer quantity;
    @JsonProperty("menu_item_id")
    private Long menuItemId;
}
