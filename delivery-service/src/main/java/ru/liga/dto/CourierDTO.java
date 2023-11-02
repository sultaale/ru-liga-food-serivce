package ru.liga.dto;

import lombok.Data;


@Data
public class CourierDTO {
    private Long id;
    private String phone;
    private String status;
    private String coordinates;
}
