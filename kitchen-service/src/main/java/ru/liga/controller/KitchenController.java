package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrdersDTO;
import ru.liga.service.KitchenService;

import javax.validation.Valid;

@Slf4j
@Tag(name = "Restaurant", description = "Restaurant API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchen")
public class KitchenController {
    private final KitchenService kitchenService;

    @Operation(summary = "Изменить статус заказа на принят")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Заказ найден, статус обновлен"),
            @ApiResponse(responseCode = "404", description = "Заказ с указанным id не найден")
    })
    @PostMapping("/orders/{id}")
    public void updateOrder(@PathVariable long id) {
        log.debug("Поиск заказа по id в OrderRepository");
        log.debug("Смена статуса заказа с id " + id);
        kitchenService.updateStatus(id);
    }

    @Operation(summary = "Передача заказа в курьерскую службу")
    @PostMapping("/orderCompleted/{orderId}")
    public void completeOrder(@Valid @PathVariable Long orderId, @RequestParam String routingKey) {
        kitchenService.completeOrder(orderId, routingKey);
    }

    @Operation(summary = "Получения списка заказов по указанному статусу")
    @GetMapping("/orders")
    @ResponseBody
    public OrdersDTO getByStatus(@RequestParam String status) {
        log.info("Поиск заказа по статусу " + status);
        return kitchenService.getOrderByStatus(status);
    }

}
