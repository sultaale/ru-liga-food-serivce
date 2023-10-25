package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.RestaurantMenuItem;

import java.util.Optional;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenuItem, Long> {

    Optional<RestaurantMenuItem> findByName(String name);
    Optional<RestaurantMenuItem> findById(Long id);
}
