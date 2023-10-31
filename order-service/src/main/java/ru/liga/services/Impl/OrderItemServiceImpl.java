package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.OrderItemMapper;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.exceptions.OrderItemNotFoundException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.repository.OrderItemRepository;
import ru.liga.services.OrderItemService;
import ru.liga.services.RestaurantMenuService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {
private final OrderItemMapper orderItemMapper;
private final OrderItemRepository orderItemRepository;
private final OrderMapper orderMapper;
private final RestaurantMenuService restaurantMenuService;

    @Override
    public Optional<OrderItem> getById(Long id) {
        return orderItemMapper.getById(id);
    }

    @Transactional
    @Override
    public Long addOrderItem(Long orderId, OrderItemForOrderCreationDTO orderItemForOrderCreationDTO) {
         Order order = orderMapper.getById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
         Long menuId = orderItemForOrderCreationDTO.getMenuItemId();
         RestaurantMenuItem restaurantMenuItem = restaurantMenuService.getById(menuId);
         OrderItem orderItem = new OrderItem();
         Double price = restaurantMenuItem.getPrice();
         Integer quantity = orderItemForOrderCreationDTO.getQuantity();
         Double totalPrice = price * quantity;

         orderItem.setOrder(order);
         orderItem.setRestaurantMenuItem(restaurantMenuItem);
         orderItem.setPrice(totalPrice);
         orderItem.setQuantity(quantity);

         orderItemRepository.save(orderItem);

         return orderItem.getId();
    }

    @Transactional
    public void deleteItem(Long id) {
        OrderItem orderItem = orderItemRepository.getOrderItemById(id).orElseThrow(()-> new OrderItemNotFoundException("Item not found"));
        orderItemRepository.delete(orderItem);
    }
}
