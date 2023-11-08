package ru.liga.services;

import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.StatusUpdateDTO;

import java.util.UUID;

public interface OrderService {
    OrderCreationResponseDTO createOrder(Long customerId, OrderCreationDTO orderCreationDTO);

    OrderDTO getById(UUID id);

    OrdersDTO getAll();

    @Transactional
    void updateStatus(StatusUpdateDTO statusUpdateDTO);

    @Transactional
    void payOrder(UUID id);
}
