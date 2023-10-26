package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.services.CourierService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/couriers")
public class CourierController {
    private final CourierService courierService;


    @PatchMapping ("/{id}")
    public void updateStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO status) {
        courierService.updateStatus(id, status);
    }
}
