package ru.liga.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusUpdateDTO {
    private Long id;
    private String status;
}
