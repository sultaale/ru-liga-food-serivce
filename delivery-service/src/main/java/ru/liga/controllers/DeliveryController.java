package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.DeliveriesDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.models.Courier;
import ru.liga.models.Order;
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
    public Order updateStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO statusUpdateDTO) {
       return courierService.updateStatus(id, statusUpdateDTO);
    }

    @PostMapping("/couriers/orders/status")
    public void updateStatus(@RequestBody StatusUpdateDTO statusUpdateDTO) {
         courierService.acceptOrder(statusUpdateDTO);
    }

    @GetMapping("/deliveries")
    public DeliveriesDTO findAllByStatus(@RequestParam String status) {
        return courierService.findAllDeliveriesByStatus(status);
    }

    @PostMapping("/courier")
    public Courier findCourier(@RequestBody StatusUpdateDTO statusUpdateDTO) {
        return courierService.findCourier(statusUpdateDTO);
    }
}
