package ru.liga.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.MenuCreationDTO;
import ru.liga.dto.RestaurantMenuDTO;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.service.RestaurantMenuService;

import javax.validation.Valid;

@Slf4j
@Tag(name = "Restaurant Menu", description = "Restaurant Menu API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/menu")
public class RestaurantMenuController {
     private final RestaurantMenuService restaurantMenuService;

     @Operation(summary = "Получить меню по id")
     @GetMapping("/{id}")
     public RestaurantMenuDTO getById(@PathVariable Long id) {
         return restaurantMenuService.getById(id);
     }

     @Operation(summary = "Изменить цену блюда")
     @PutMapping("/{id}")
     public void updatePrice(@PathVariable Long id, @RequestParam Double price) {
         log.warn("Процесс обновления цены");
         restaurantMenuService.updatePrice(id, price);
     }

     @Operation(summary = "Удалить меню")
     @DeleteMapping("/{id}")
     public void deleteRestaurantMenuItem(@PathVariable Long id) {
        restaurantMenuService.deleteById(id);
     }

     @Operation(summary = "Добавить новое меню")
     @PostMapping()
     public void addMenu(@Valid @RequestBody MenuCreationDTO menuCreationDTO) {
         restaurantMenuService.addMenu(menuCreationDTO);
    }
}
