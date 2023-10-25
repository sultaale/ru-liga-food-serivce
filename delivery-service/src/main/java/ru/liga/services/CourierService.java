package ru.liga.services;

import ru.liga.dto.CourierDTO;
import ru.liga.dto.StatusUpdateDTO;

import java.util.List;


public interface CourierService {
    CourierDTO getById(Long id);

    CourierDTO getByPhone(String phone);

    List<CourierDTO> getAllCouriers();

    CourierDTO findByStatus(String status);

    CourierDTO updateStatus(Long id, String status);

    void updateStatusOrder(Long id, StatusUpdateDTO statusUpdateDTO);
}
