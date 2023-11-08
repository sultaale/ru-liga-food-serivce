package ru.liga.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.entity.Order;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.repository.OrderRepository;
import ru.liga.service.OrderService;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;


    @Override
    @Transactional
    public void acceptOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
        order.setStatus(OrderStatus.KITCHEN_ACCEPTED);
        orderRepository.save(order);
        rabbitTemplate.convertAndSend("directExchange", "update.order", id.toString());
    }

    @Override
    @Transactional
    public void declineOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException(id));
        order.setStatus(OrderStatus.KITCHEN_DENIED);
        orderRepository.save(order);
        rabbitTemplate.convertAndSend("directExchage","update.order", id.toString());
    }

    @Override
    @Transactional
    public void completedOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
        order.setStatus(OrderStatus.DELIVERY_PENDING);
        orderRepository.save(order);
        rabbitTemplate.convertAndSend("directExchange", "courier.find", id.toString());
    }

}
