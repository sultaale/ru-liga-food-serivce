package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.models.Order;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.OrderRepository;
import ru.liga.services.OrderService;
import ru.liga.util.Converter;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final Converter converter;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
    
    @Override
    public Optional<Order> findByRestaurantId(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderMapper.getById(id);
        
        return order;
    }

    @Override
    public List<Order> getByStatus(String status) {
        return orderRepository.findAllByStatus(OrderStatus.valueOf(status));
    }


    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }


}
