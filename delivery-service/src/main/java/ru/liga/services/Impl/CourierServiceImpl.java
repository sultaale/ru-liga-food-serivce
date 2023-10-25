package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.exceptions.CourierNotFoundException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.exceptions.StatusIsNotValidException;
import ru.liga.models.Courier;

import ru.liga.models.Order;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.services.CourierService;
import ru.liga.utils.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final Converter converter;


    @Override
    public CourierDTO getById(Long id) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found by id " + id));
        return converter.toDTO(courier);
    }

    @Override
    public CourierDTO getByPhone(String phone) {
       Courier courier = courierRepository.findByPhone(phone)
               .orElseThrow(() -> new CourierNotFoundException("Courier not found"));
       return converter.toDTO(courier);
    }

    @Override
    public List<CourierDTO> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public CourierDTO findByStatus(String status) {
        Courier courier = courierRepository.findFirstByStatus(status).orElseThrow();
        return converter.toDTO(courier);
    }

    @Transactional
    @Override
    public CourierDTO updateStatus(Long id, String status) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found"));

        courier.setStatus(status);
        return converter.toDTO(courier);
    }


    @Transactional
    @Override
    public void acceptOrder(StatusUpdateDTO statusUpdateDTO) {
        Long orderId = statusUpdateDTO.getId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));

        String status = statusUpdateDTO.getStatus();
        Optional<OrderStatus> newStatus = Arrays.stream(OrderStatus.values())
                .filter(s -> s.toString().equalsIgnoreCase(status)).findAny();
        if (newStatus.isPresent()) {
            order.setStatus(newStatus.get());
            orderRepository.save(order);
        } else {
        throw new StatusIsNotValidException("Status is not valid"); }
    }

}
