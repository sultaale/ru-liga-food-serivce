package ru.liga.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Order;
import ru.liga.models.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

    List<Order> findAllByStatus(OrderStatus status);
}
