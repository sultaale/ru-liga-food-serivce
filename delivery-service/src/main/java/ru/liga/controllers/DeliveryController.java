package ru.liga.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.DeliveriesDTO;


@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @GetMapping()
    public ResponseEntity<DeliveriesDTO> getDeliveriesById(@RequestParam String status) {
        DeliveriesDTO deliveriesDTO = new DeliveriesDTO();
        return ResponseEntity.ok(deliveriesDTO);
    }

    @PostMapping("/{id}")
    public HttpStatus changeDeliveryStatus(@PathVariable int id){
         return HttpStatus.OK;
    }
}
