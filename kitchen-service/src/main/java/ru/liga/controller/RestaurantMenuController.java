package ru.liga.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.MenuCreationDTO;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.services.RestaurantMenuService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/menu")
public class RestaurantMenuController {
     private final RestaurantMenuService restaurantMenuService;

     @GetMapping("/{id}")
     public RestaurantMenuItem getById(@PathVariable Long id) {
         return restaurantMenuService.getById(id);
     }

     @PostMapping("/{id}")
     public RestaurantMenuItem updatePrice(@PathVariable Long id, @RequestParam double price) {
         return restaurantMenuService.updatePrice(id, price);
     }

     @DeleteMapping("/{id}")
     public void deleteRestaurantMenuItem(@PathVariable Long id) {
        restaurantMenuService.deleteById(id);
     }

     @PostMapping()
     public void addMenu(@Valid @RequestBody MenuCreationDTO menuCreationDTO) {
         restaurantMenuService.addMenu(menuCreationDTO);
         log.info(menuCreationDTO.getRestaurantId().toString());
    }
}
