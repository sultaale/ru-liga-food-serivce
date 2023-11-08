package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    Optional<OrderItem> getOrderItemById(Long id);
    List<OrderItem> findOrderItemByOrder(Order order);
}
