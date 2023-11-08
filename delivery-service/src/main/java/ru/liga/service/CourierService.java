package ru.liga.service;

import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.OrdersDTO;

import java.util.UUID;

public interface CourierService {
    OrdersDTO getAll();

    @Transactional
    void acceptOrder(UUID id);
}
