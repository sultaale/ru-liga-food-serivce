package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CourierDTO;
import ru.liga.services.CourierService;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class DeliveryController {
    private final CourierService courierService;



    @GetMapping("/couriers/status/{status}")
    public CourierDTO getByStatus(@PathVariable String status) {
        return courierService.findByStatus(status);
    }

    @GetMapping("/couriers")
    public List<CourierDTO> getAll() {
        return courierService.getAllCouriers();
    }

    
    @PostMapping("/couriers/status/{id}")
    public CourierDTO updateStatus(@PathVariable Long id, String statusUpdateDTO) {
       return courierService.updateStatus(id, statusUpdateDTO);
    }
}
