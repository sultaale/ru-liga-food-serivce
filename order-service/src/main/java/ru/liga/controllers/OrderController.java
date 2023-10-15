package ru.liga.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.util.Converter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final Converter converter;

    public OrderController(Converter converter) {
        this.converter = converter;
    }

    @GetMapping("")
    public ResponseEntity<OrdersDTO> getAllOrders(){

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

        OrderDTO orderDTO = converter.toDTO(new Order());

        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderCreationResponseDTO> order(@RequestBody OrderCreationDTO orderCreationDTO) {

        OrderItem orderItem = converter.toEntity(orderCreationDTO);
        OrderCreationResponseDTO orderCreationResponseDTO = new OrderCreationResponseDTO();

        return ResponseEntity.ok(orderCreationResponseDTO);
     }
}
