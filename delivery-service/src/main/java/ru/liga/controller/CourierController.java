package ru.liga.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrdersDTO;
import ru.liga.service.CourierService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/delivery")
public class CourierController {
     private final CourierService courierService;

     @GetMapping()
     public OrdersDTO getOrders() {
         return courierService.getAll();
     }

     @PostMapping("/{id}/take")
     public void acceptOrder(@PathVariable UUID id) {
         courierService.acceptOrder(id);
     }


}
