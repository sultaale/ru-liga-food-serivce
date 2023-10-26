package ru.liga.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String phone;
    private String email;
    private String address;
}
