package ru.liga.services;


import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderDTO getById(Long id);
    Optional<Order> findByRestaurantId(Long id);
    List<Long> getByStatus(String status);
    OrderCreationResponseDTO createOrder(Long customerId,OrderCreationDTO orderCreationDTO);
    OrdersDTO getAll();
    void sendOrderNotification(Long orderId);
}
