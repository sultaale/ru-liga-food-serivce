package ru.liga.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.models.Customer;
import ru.liga.services.CustomerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.getById(id);
    }

    @GetMapping("/phone/{phone}")
    public Customer getCustomerByPhone(@PathVariable("phone") String phone) {
        return customerService.getByPhone(phone);
    }
}
