package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.exceptions.RestaurantNotFoundException;
import ru.liga.models.Restaurant;
import ru.liga.repository.RestaurantRepository;
import ru.liga.services.RestaurantService;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant getById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurant;
    }
}
