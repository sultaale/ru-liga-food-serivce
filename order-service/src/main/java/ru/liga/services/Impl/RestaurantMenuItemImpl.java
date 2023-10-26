package ru.liga.services.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.exceptions.RestaurantMenuException;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.repository.RestaurantMenuRepository;
import ru.liga.services.RestaurantMenuService;
import ru.liga.util.Converter;

import java.util.InvalidPropertiesFormatException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantMenuItemImpl implements RestaurantMenuService {
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final Converter converter;

    @Override
    public RestaurantMenuItem getById(Long id) {
        return restaurantMenuRepository.findById(id).orElseThrow(() -> new RestaurantMenuException("Menu not found by id " + id));
    }

    @Override
    public RestaurantMenuItem getByName(String name) {
        return restaurantMenuRepository.findByName(name).orElseThrow(() -> new RestaurantMenuException("Menu not found by name" + name));
    }

    @Transactional
    public RestaurantMenuItem updatePrice(Long id, double price) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuRepository.findById(id).orElseThrow(() -> new RestaurantMenuException("Menu not found"));
       restaurantMenuItem.setPrice(price);
       restaurantMenuRepository.save(restaurantMenuItem);
        return restaurantMenuItem;
    }

    @Transactional
    public void deleteById(Long id) {
        restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new RestaurantMenuException("Menu not found by id"));
        restaurantMenuRepository.deleteById(id);
    }
}
