package ru.liga.services;


import ru.liga.dto.MenuCreationDTO;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.models.OrderItem;

import java.util.Optional;

public interface OrderItemService {

    Optional<OrderItem> getById(Long id);

    Long addOrderItem(Long orderId, OrderItemForOrderCreationDTO orderItemForOrderCreationDTO);

    void deleteItem(Long id);
}
