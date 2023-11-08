package ru.liga.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.entity.Order;
import ru.liga.services.Impl.OrderServiceImpl;
import ru.liga.services.OrderService;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Tag(name = "API для управления заказом")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;
    private final OrderService orderService;

    @Operation(summary = "Добавление нового заказа с указанием id заказчика")
    @PostMapping
    public OrderCreationResponseDTO createOrder(@RequestParam Long customerId, @RequestBody OrderCreationDTO orderCreationDTO) {
     return orderService.createOrder(customerId, orderCreationDTO);
    }

    @Operation(summary = "Получение заказа по id")
    @GetMapping({"/{id}"})
    public OrderDTO getById(@PathVariable UUID id) {
        return orderService.getById(id);
    }

    @Operation(summary = "Просмотр списка заказов")
    @GetMapping
    public OrdersDTO getAll() {
       return orderService.getAll();
    }

    @PutMapping("/status")
    public void updateStatus(@RequestBody StatusUpdateDTO statusUpdateDTO) {
        orderService.updateStatus(statusUpdateDTO);
    }

    @PutMapping("customers/{customerId}")
    public void pay(@PathVariable Long customerId, @RequestParam UUID id) {
        orderService.payOrder(id);
    }

}
