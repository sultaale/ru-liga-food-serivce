package ru.liga.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.liga.models.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> getOrderItemById(Long id);

     Optional<OrderItem> getByRestaurantMenuItemId(Long id);

     Optional<OrderItem> getByOrder_Id(Long id);
}
