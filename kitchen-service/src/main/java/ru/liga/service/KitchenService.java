package ru.liga.service;


import ru.liga.dto.MenuCreationDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.models.RestaurantMenuItem;

public interface KitchenService {

    void updateStatus(Long id);

    void completeOrder(Long orderId, String routingKey);

    OrdersDTO getOrderByStatus(String status);

    RestaurantMenuItem getById(Long id);

    RestaurantMenuItem getByName(String name);

    RestaurantMenuItem updatePrice(Long id, double price);

    void addMenu(MenuCreationDTO menuCreationDTO);

    void deleteById(Long id);
}
