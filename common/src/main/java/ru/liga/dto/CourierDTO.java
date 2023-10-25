package ru.liga.dto;

import lombok.Data;
import org.postgresql.geometric.PGpoint;

@Data
public class CourierDTO {
    private Long id;
    private String phone;
    private String status;
    private PGpoint coordinates;
}
