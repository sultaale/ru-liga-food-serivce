package ru.liga.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.models.Order;
import ru.liga.util.Converter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @GetMapping()
    public ResponseEntity<OrdersDTO> getAllOrders(@RequestParam(name = "pageIndex", defaultValue = "0") Integer pageIndex,
                                                  @RequestParam(name = "pageCount", defaultValue = "10") Integer pageCount){

        OrdersDTO orders = new OrdersDTO();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setRestaurant(new RestaurantDTO());
        orderDTO.setItems(new ArrayList<>());
        orders.setOrders(List.of(orderDTO));

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {

        OrderDTO orderDTO = new Converter(new ModelMapper()).convertOrderToOderDTO(new Order());
        return ResponseEntity.ok(orderDTO);
    }
    
}
