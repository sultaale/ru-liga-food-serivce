package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Restaurant;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Override
    Optional<Restaurant> findById(Long id);

    Optional<Restaurant> findAllByStatus(String status);
}
