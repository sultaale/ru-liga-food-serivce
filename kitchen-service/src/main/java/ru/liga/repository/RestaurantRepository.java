package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAllByStatus(String status);
}
