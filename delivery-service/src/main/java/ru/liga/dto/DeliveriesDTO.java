package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.liga.models.Order;

import java.util.List;

@Data
public class DeliveriesDTO {
    @JsonProperty("delivery")
    List<OrderDTO> orders;
    @JsonProperty("page_index")
    private int index;
    @JsonProperty("page_count")
    private int count;
}
