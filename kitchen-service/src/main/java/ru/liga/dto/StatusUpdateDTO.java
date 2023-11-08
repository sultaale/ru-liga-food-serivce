package ru.liga.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class StatusUpdateDTO {
    private UUID id;
    private String status;
}
