package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDTO {
    private List<OrderDTO> orders;
    @JsonProperty("page_index")
    private int pageIndex;
    @JsonProperty("page_count")
    private int pageCount;
}
