package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.entity.RestaurantMenuItem;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenuItem, Long> {
}
