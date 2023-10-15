package ru.liga.models;

import lombok.Getter;
import lombok.Setter;
import java.awt.*;

@Getter
@Setter
public class Courier {
    private Long id;
    private String phone;
    private String status;
    private Point point;
}
