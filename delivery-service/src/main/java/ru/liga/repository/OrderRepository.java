package ru.liga.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Order;
import ru.liga.models.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(OrderStatus orderStatus, Pageable page);
    List<Order> findAllByStatus(OrderStatus orderStatus);
    Optional<Order> findById(Long id);
}
