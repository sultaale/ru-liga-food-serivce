package ru.liga.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.services.RestaurantMenuService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/menu")
public class RestaurantMenuController {
     private final RestaurantMenuService restaurantMenuService;

     @GetMapping("/{id}")
     public RestaurantMenuItem getById(@PathVariable Long id) {
         return restaurantMenuService.getById(id);
     }

     @PatchMapping("/{id}")
     public RestaurantMenuItem updatePrice(@PathVariable Long id, @RequestParam double price) {
         return restaurantMenuService.updatePrice(id, price);
     }

     @DeleteMapping("/{id}")
     public void deleteRestaurantMenuItem(@PathVariable Long id) {
        restaurantMenuService.deleteById(id);
     }

}
