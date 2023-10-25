package ru.liga.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.clients.KitchenClient;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.OrderRepository;
import ru.liga.service.KitchenService;


@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    private final KitchenClient kitchenClient;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void updateStatus(Long orderId) {
        StatusUpdateDTO statusUpdateDTO = StatusUpdateDTO.builder().id(orderId).status(OrderStatus.KITCHEN_ACCEPTED.toString()).build();
        kitchenClient.updateStatus(statusUpdateDTO);
    }
}
