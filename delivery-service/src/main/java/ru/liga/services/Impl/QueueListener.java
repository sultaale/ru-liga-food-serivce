package ru.liga.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.exceptions.CourierNotFoundException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.models.Courier;
import ru.liga.models.Order;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;



@Service
@RequiredArgsConstructor
public class QueueListener {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @RabbitListener(queues = {"courier1", "courier2"})
    public void findCouriers(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long orderId = objectMapper.readValue(message, Long.class);

        Courier courier = courierRepository.findFirstByStatus("COMPLETE").orElseThrow(() -> new CourierNotFoundException("available couriers not found"));

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException(orderId));
        order.setCourier(courier);
        orderRepository.save(order);
    }
}
