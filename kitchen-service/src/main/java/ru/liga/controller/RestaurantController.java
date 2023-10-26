package ru.liga.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrdersDTO;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    @GetMapping("/orders")
    public OrdersDTO getOrdersByStatus(@RequestParam String status) {
        OrdersDTO ordersDTO = new OrdersDTO();

        return ordersDTO;
    }

}
