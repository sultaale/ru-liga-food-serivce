package ru.liga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.service.KitchenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kitchen")
public class KitchenController {
    private final KitchenService kitchenService;

    @PostMapping("/orders/{id}")
    public void updateOrder(@PathVariable long id) {
        kitchenService.updateStatus(id);
    }
    
}
