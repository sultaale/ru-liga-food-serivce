package ru.liga.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.liga.models.Order;
import ru.liga.models.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id from Order o  WHERE o.status =:status")
    List<Long> findAllByStatus(OrderStatus status);

    List<Order> findAllByCustomer_Id(Long customerId);
}
