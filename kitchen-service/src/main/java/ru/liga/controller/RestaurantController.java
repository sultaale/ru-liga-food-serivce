package ru.liga.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.service.OrderService;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@Tag(name = "Restaurant", description = "Restaurant API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchen")
public class RestaurantController {
    private final OrderService orderService;

    @PutMapping("/{id}/accepted")
    public void acceptOrder(@PathVariable @Valid UUID id) {
      orderService.acceptOrder(id);
    }

    @PutMapping("/{id}/declined")
    public void declineOrder(@PathVariable UUID id) {
        orderService.declineOrder(id);
    }

    @PutMapping("/{id}/ready")
    public void completedOrder(@PathVariable UUID id) {
        orderService.completedOrder(id);
    }
}
