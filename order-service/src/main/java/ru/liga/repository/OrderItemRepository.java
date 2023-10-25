package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.OrderItem;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
     Optional<OrderItem> getOrderItemById(Long id);
}
