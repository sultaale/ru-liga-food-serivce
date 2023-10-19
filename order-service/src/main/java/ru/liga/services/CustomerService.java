package ru.liga.services;

import ru.liga.models.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(Long id);

    Customer getByPhone(String phone);

    List<Customer> getAllCustomers();
}
