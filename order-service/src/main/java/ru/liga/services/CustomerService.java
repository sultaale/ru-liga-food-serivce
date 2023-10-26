package ru.liga.services;

import ru.liga.dto.CustomerDTO;

import java.util.List;


public interface CustomerService {
    CustomerDTO getById(Long id);

    CustomerDTO getByPhone(String phone);

    List<CustomerDTO> getAllCustomers();

}
