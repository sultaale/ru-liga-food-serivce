package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {
   Optional<Courier> findFirstByStatus(String status);

   Optional<Courier> findByPhone(String phone);

   Optional<Courier> findById(Long id);

   List<Courier> findAllByStatus(String status);
}
