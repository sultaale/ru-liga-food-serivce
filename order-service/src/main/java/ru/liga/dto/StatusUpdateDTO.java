package ru.liga.dto;

import lombok.Data;

@Data
public class StatusUpdateDTO {
    private Long orderId;
    private String status;
}
