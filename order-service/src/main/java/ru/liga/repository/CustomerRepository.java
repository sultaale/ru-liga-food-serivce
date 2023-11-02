package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.liga.models.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String number);

    Optional<Customer> findById(Long id);


}
