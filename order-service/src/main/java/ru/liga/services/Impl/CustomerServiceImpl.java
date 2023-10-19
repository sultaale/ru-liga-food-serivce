package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.exceptions.CustomerNotFoundException;
import ru.liga.models.Customer;
import ru.liga.repository.CustomerRepository;
import ru.liga.services.CustomerService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    
     @Override
     public Customer getById(Long id) {
        return customerRepository.findById(id).
                orElseThrow(() ->new CustomerNotFoundException(id));
     }

    @Override
    public Customer getByPhone(String phone) {
        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}
