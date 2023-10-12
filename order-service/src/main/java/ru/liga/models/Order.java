package ru.liga.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private Customer customer;
    private Restaurant restaurant;
    private Courier courier;
    private String status;
    private LocalDateTime timestamp;
    private List<OrderItem> items;
}
