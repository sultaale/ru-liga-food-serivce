package ru.liga.service;


import ru.liga.dto.MenuCreationDTO;
import ru.liga.dto.RestaurantMenuDTO;
import ru.liga.models.RestaurantMenuItem;

public interface RestaurantMenuService {
    RestaurantMenuDTO getById(Long id);

    RestaurantMenuItem getByName(String name);

    void updatePrice(Long id, Double price);

    void addMenu(MenuCreationDTO menuCreationDTO);

    void deleteById(Long id);
}
