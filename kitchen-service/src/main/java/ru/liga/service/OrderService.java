package ru.liga.service;

import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.StatusUpdateDTO;

import java.util.UUID;

public interface OrderService {

    void acceptOrder(UUID id);

    @Transactional
    void declineOrder(UUID id);

    @Transactional
    void completedOrder(UUID id);

}
