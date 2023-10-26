package ru.liga.services;

import ru.liga.dto.CourierDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.models.Courier;

import java.util.List;


public interface CourierService {
    CourierDTO getById(Long id);

    CourierDTO getByPhone(String phone);

    List<CourierDTO> getAllCouriers();

    CourierDTO findByStatus(String status);

    void updateStatus(Long id, StatusUpdateDTO statusUpdateDTO);
}
