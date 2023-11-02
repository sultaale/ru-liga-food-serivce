package ru.liga.services;

import ru.liga.dto.CourierDTO;
import ru.liga.dto.DeliveriesDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.models.Courier;
import ru.liga.models.Order;

import java.util.List;


public interface CourierService {
    CourierDTO getById(Long id);

    CourierDTO getByPhone(String phone);

    List<CourierDTO> getAllCouriers();

    CourierDTO findByStatus(String status);

    Order updateStatus(Long id, StatusUpdateDTO statusUpdateDTO);

    void acceptOrder(StatusUpdateDTO statusUpdateDTO);

    DeliveriesDTO findAllDeliveriesByStatus(String status);

    Courier findCourier(StatusUpdateDTO statusUpdateDTO);

}
