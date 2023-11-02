package ru.liga.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.OrderItemMapper;
import ru.liga.clients.KitchenClient;
import ru.liga.dto.MenuCreationDTO;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderItemForCreationDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.exceptions.InvalidPropertyException;
import ru.liga.exceptions.RestaurantMenuException;
import ru.liga.exceptions.RestaurantNotFoundException;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantMenuRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.service.KitchenService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KitchenServiceImpl implements KitchenService {
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final KitchenClient kitchenClient;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public void updateStatus(Long orderId) {
        StatusUpdateDTO statusUpdateDTO = StatusUpdateDTO.builder().id(orderId).status(OrderStatus.KITCHEN_ACCEPTED.toString()).build();
        kitchenClient.updateStatus(statusUpdateDTO);
    }

     @Override
     public void completeOrder(Long orderId, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, orderId);
     }

     @Override
     public OrdersDTO getOrderByStatus(String status) {
         OrderStatus orderStatus = OrderStatus.valueOf(status);
         log.warn("order status not valid " + OrderStatus.valueOf(status));
         OrdersDTO ordersDTO = new OrdersDTO();
         List<OrderCreationDTO> orderDTOS = new ArrayList<>();
         List<Order> orders = orderRepository.findAllByStatus(orderStatus);

         for (Order order: orders) {
             OrderCreationDTO orderDTO = new OrderCreationDTO();
             Long orderId = order.getId();
             orderDTO.setId(orderId);
             log.info("Order id " + order.getId());

             List<OrderItem> orderItems = orderItemMapper.getByOrderId(orderId);
             List<OrderItemForCreationDTO> itemDTOs = new ArrayList<>();
             for (OrderItem item: orderItems) {
                 OrderItemForCreationDTO orderItemDTO = new OrderItemForCreationDTO();
                 orderItemDTO.setQuantity(item.getQuantity());
                 orderItemDTO.setId(item.getRestaurantMenuItem().getId());
                 itemDTOs.add(orderItemDTO);
             }
             orderDTO.setOrderItems(itemDTOs);
             orderDTOS.add(orderDTO);
         }
         ordersDTO.setOrders(orderDTOS);
         return ordersDTO;
     }

    @Override
    public RestaurantMenuItem getById(Long id) {
        return restaurantMenuRepository.findById(id).orElseThrow(() -> new RestaurantMenuException("Menu not found by id " + id));
    }

    @Override
    public RestaurantMenuItem getByName(String name) {
        return restaurantMenuRepository.findByName(name).orElseThrow(() -> new RestaurantMenuException("Menu not found by name" + name));
    }

    @Transactional
    public RestaurantMenuItem updatePrice(@Valid Long id, double price) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuRepository.findById(id).orElseThrow(() -> new RestaurantMenuException("Menu not found"));

        if (price <= 0.0)
            throw new InvalidPropertyException("Please check price " + price);

        restaurantMenuItem.setPrice(price);
        restaurantMenuRepository.save(restaurantMenuItem);
        return restaurantMenuItem;
    }

    @Transactional
    public void addMenu(MenuCreationDTO menuCreationDTO) {
        RestaurantMenuItem menu = new RestaurantMenuItem();

        Restaurant restaurant = restaurantRepository.findById(menuCreationDTO.getRestaurantId())
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found"));
        menu.setRestaurant(restaurant);
        menu.setName(menuCreationDTO.getName());
        menu.setPrice(menuCreationDTO.getPrice());
        menu.setImage(menuCreationDTO.getImage());
        menu.setDescription(menuCreationDTO.getDescription());

        restaurantMenuRepository.save(menu);
    }

    @Transactional
    public void deleteById(Long id) {
        restaurantMenuRepository.findById(id)
                .orElseThrow(() -> new RestaurantMenuException("Menu not found by id"));

        restaurantMenuRepository.deleteById(id);
    }
}
