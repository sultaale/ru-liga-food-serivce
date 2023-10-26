package ru.liga.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.CustomerDTO;
import ru.liga.services.CustomerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping("/phone/{phone}")
    public CustomerDTO getByPhone(@PathVariable String phone) {
        return customerService.getByPhone(phone);
    }
}
