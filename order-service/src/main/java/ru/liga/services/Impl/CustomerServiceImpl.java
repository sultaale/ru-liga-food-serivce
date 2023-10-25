package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.CustomerDTO;
import ru.liga.exceptions.CustomerNotFoundException;
import ru.liga.models.Customer;
import ru.liga.repository.CustomerRepository;
import ru.liga.services.CustomerService;
import ru.liga.util.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Converter converter;
    
     @Override
     public CustomerDTO getById(Long id) {
         Customer customer = customerRepository.findById(id).
                 orElseThrow(() ->new CustomerNotFoundException(id));
        return converter.toDTO(customer);
     }

    @Override
    public CustomerDTO getByPhone(String phone) {
        Customer customer = customerRepository.findByPhone(phone)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found by phone number"));
        return converter.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
         return customerRepository.findAll().stream()
                 .map(converter::toDTO).collect(Collectors.toList());
    }
}
