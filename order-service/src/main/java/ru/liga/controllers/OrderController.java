package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
import ru.liga.services.OrderService;
import ru.liga.util.Converter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/status/{status}")
    public List<Order> getByStatus(@PathVariable String status) {
       return orderService.getByStatus(status);
     }

    @GetMapping("restaurants/{id}")
    public Order getAllOrders(@PathVariable Long id){

//
//        OrdersDTO orders = new OrdersDTO();
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(1L);
//        orderDTO.setRestaurant(new RestaurantDTO());
//        orderDTO.setItems(new ArrayList<>());
//        orders.setOrders(List.of(orderDTO));

        return orderService.findByRestaurantId(id).orElse(null);
    }

    

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id).orElse(null);
    }

    @PostMapping()
    public ResponseEntity<OrderCreationResponseDTO> order(@RequestBody OrderCreationDTO orderCreationDTO) {
        Converter converter = new Converter(new ModelMapper());
        OrderItem orderItem = converter.toEntity(orderCreationDTO);
        OrderCreationResponseDTO orderCreationResponseDTO = new OrderCreationResponseDTO();

        return ResponseEntity.ok(orderCreationResponseDTO);
     }
}
