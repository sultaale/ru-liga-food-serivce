package ru.liga.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import ru.liga.dto.RestaurantDTO;
import ru.liga.entity.Order;

@RequiredArgsConstructor
public class Converter{
private final ModelMapper modelMapper;


    public RestaurantDTO toDto(Order order) {
        return modelMapper.map(order.getRestaurant(), RestaurantDTO.class);
    }
}