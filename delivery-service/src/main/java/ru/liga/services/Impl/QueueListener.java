package ru.liga.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.exceptions.CourierNotFoundException;
import ru.liga.exceptions.DeliveryException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.models.Courier;
import ru.liga.models.Order;
import ru.liga.models.Restaurant;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.utils.DistanceCalculator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueListener {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    @RabbitListener(queues = {"courier1", "courier2"})
    public void findCouriers(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long orderId = objectMapper.readValue(message, Long.class);
        log.info("orderId " + orderId);
//        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException(orderId));

//        List<Courier> couriers = courierRepository.findAllByStatus(OrderStatus.DELIVERY_PENDING.toString());
//        Map<Long, Double> setOfDistance = new HashMap<>();
                
    }
}
