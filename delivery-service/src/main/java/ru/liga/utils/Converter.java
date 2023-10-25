package ru.liga.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.ComponentScan;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.CustomerDTO;
import ru.liga.models.Courier;
import ru.liga.models.Customer;

@ComponentScan(basePackages = "repository")
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;

    public CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CourierDTO toDTO(Courier courier) {
        return modelMapper.map(courier, CourierDTO.class);
    }

    public Courier toEntity(CourierDTO courierDTO) {
        Courier courier = new Courier();
        courier.setId(courierDTO.getId());
        courier.setStatus(courierDTO.getStatus());
        courier.setPhone(courierDTO.getPhone());
        courier.setCoordinates(courierDTO.getCoordinates());

        return courier;
    }

}
