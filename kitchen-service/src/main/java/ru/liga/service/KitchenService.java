package ru.liga.service;

import ru.liga.dto.OrderDTO;
import ru.liga.dto.StatusUpdateDTO;

public interface KitchenService {
    OrderDTO assignCourier(Long orderId);

}
