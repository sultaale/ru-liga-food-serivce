package ru.liga.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.clients.KitchenClient;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.models.Order;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.OrderRepository;
import ru.liga.service.KitchenService;
import ru.liga.utils.Converter;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    private final KitchenClient kitchenClient;
    private final OrderRepository orderRepository;
    private final Converter converter;

    @Transactional
    @Override
    public OrderDTO assignCourier(Long orderId) {
       CourierDTO courierDTO = kitchenClient.getByStatus("DELIVERY_COMPLETED");
       Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderRepository.save(order);
        return converter.toDTO(order);
    }
}
