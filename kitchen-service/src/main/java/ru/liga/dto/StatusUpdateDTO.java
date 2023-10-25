package ru.liga.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatusUpdateDTO {
    private String status;
}
