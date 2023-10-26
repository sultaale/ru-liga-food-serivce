package ru.liga.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.liga.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(Long id);
    Optional<Order> findByRestaurantId(Long id);
    List<Order> getByStatus(String status);
    Order getOrderById(Long id);
    List<Order> getAll();

}
