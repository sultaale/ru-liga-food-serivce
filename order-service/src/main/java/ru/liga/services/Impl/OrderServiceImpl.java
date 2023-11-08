package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ru.liga.batisMapper.OrderItemMapper;
//import ru.liga.batisMapper.OrderMapper;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.entity.Customer;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.exceptions.CustomerNotFoundException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.exceptions.RestaurantMenuException;
import ru.liga.exceptions.RestaurantNotFoundException;
import ru.liga.exceptions.StatusNotFoundException;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantMenuRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.services.OrderService;
import ru.liga.utils.Converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
//    private final OrderMapper orderMapper;
//    private final OrderItemMapper orderItemMapper;
    private final Converter converter;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final OrderItemRepository orderItemRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public OrderCreationResponseDTO createOrder(Long customerId, OrderCreationDTO orderCreationDTO) {
        long restaurantId = orderCreationDTO.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new RestaurantNotFoundException("Restaurant not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setCourier(null);
        order.setStatus(OrderStatus.CUSTOMER_CREATED);
        order.setTimestamp(LocalDateTime.now());
        orderRepository.save(order);

        OrderItemForOrderCreationDTO menu = orderCreationDTO.getMenuItems();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            RestaurantMenuItem restaurantMenuItem = restaurantMenuRepository.findById(menu.getMenuItemId()).orElseThrow(() -> new RestaurantMenuException("Menu not found"));
            orderItem.setRestaurantMenuItem(restaurantMenuItem);
            orderItem.setQuantity(menu.getQuantity());
            orderItem.setPrice(restaurantMenuItem.getPrice() * menu.getQuantity());

        orderItemRepository.save(orderItem);

        OrderCreationResponseDTO responseDTO = new OrderCreationResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setUrl("https://securepay.tinkoff.ru/v2/Submit3DSAuthorization");
        responseDTO.setTime(LocalDateTime.now().plusHours(1));

         sendOrderNotification(order.getId().toString());

        return responseDTO;
    }

    @Override
    public OrderDTO getById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        RestaurantDTO restaurantDTO = converter.toDto(order);
        orderDTO.setRestaurant(restaurantDTO);
        orderDTO.setTimestamp(order.getTimestamp());
        List<OrderItem> orderItems = orderItemRepository.findOrderItemByOrder(order);
        System.out.println(orderItems.get(0));
        List<OrderItemDTO> itemDTOs = new ArrayList<>();
        for (OrderItem item: orderItems) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setPrice(item.getPrice());
            orderItemDTO.setQuantity(item.getQuantity());
            orderItemDTO.setDescription(item.getRestaurantMenuItem().getDescription());
            orderItemDTO.setImage(item.getRestaurantMenuItem().getImage());
            itemDTOs.add(orderItemDTO);
        }
        orderDTO.setItems(itemDTOs);

        return orderDTO;
    }

    @Override
    public OrdersDTO getAll() {
        OrdersDTO ordersDTO = new OrdersDTO();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByStatus();

        for (Order order: orders) {

            OrderDTO orderDTO = new OrderDTO();
            UUID orderId = order.getId();
            orderDTO.setId(orderId);
            log.info("Order id " + order.getId());
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setName(order.getRestaurant().getName());
            orderDTO.setRestaurant(restaurantDTO);
            orderDTO.setTimestamp(order.getTimestamp());
            List<OrderItem> orderItems = orderItemRepository.findOrderItemByOrder(order);
            List<OrderItemDTO> itemDTOs = new ArrayList<>();
            for (OrderItem item: orderItems) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setPrice(item.getPrice());
                orderItemDTO.setQuantity(item.getQuantity());
                orderItemDTO.setDescription(item.getRestaurantMenuItem().getDescription());
                orderItemDTO.setImage(item.getRestaurantMenuItem().getImage());
                itemDTOs.add(orderItemDTO);
            }
            orderDTO.setItems(itemDTOs);
            orderDTOS.add(orderDTO);
        }
        ordersDTO.setOrders(orderDTOS);
        return ordersDTO;
    }

    @Override
    @Transactional
    public void updateStatus(StatusUpdateDTO statusUpdateDTO) {
        UUID orderId = statusUpdateDTO.getId();
        OrderStatus orderStatus = convertToOrderStatus(statusUpdateDTO.getStatus());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));

        order.setStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void payOrder(UUID id){
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
        order.setStatus(OrderStatus.CUSTOMER_PAID);
        orderRepository.save(order);
        rabbitTemplate.convertAndSend("directExchange", "paidOrder", id.toString());
        log.info("Send message that order is paid");
    }

    public void sendOrderNotification(String orderId) {
        rabbitTemplate.convertAndSend("directExchange", "restaurants", orderId);
        log.info("Send notification about new order");
    }

    public OrderStatus convertToOrderStatus(String status) {
        return Arrays.stream(OrderStatus.values())
                .filter(s->s.toString().equalsIgnoreCase(status)).findAny()
                .orElseThrow(() -> new StatusNotFoundException("Provided status is not valid"));
    }

}
