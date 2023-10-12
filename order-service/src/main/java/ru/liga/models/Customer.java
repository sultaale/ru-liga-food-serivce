package ru.liga.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long id;
    private String phone;
    private String email;
    private String address;
}
