package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.OrderItemMapper;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.exceptions.CustomerNotFoundException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.exceptions.RestaurantMenuException;
import ru.liga.models.Customer;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.CustomerRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.repository.RestaurantMenuRepository;
import ru.liga.services.CustomerService;
import ru.liga.services.OrderService;
import ru.liga.services.RestaurantService;
import ru.liga.utils.Converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final CustomerRepository customerRepository;
    private final RestaurantService restaurantService;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerService customerService;
    private final Converter converter;


    @Override
    public Optional<Order> findByRestaurantId(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderDTO getById(Long id) {
        Order order = orderMapper.getById(id).orElseThrow(()-> new OrderNotFoundException(id));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(order.getRestaurant().getName());
        orderDTO.setRestaurant(restaurantDTO);
        orderDTO.setTimestamp(order.getTimestamp());
        List<OrderItem> orderItems = orderItemMapper.getByOrderId(id);
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
    public List<Long> getByStatus(String status) {
        return orderRepository.findAllByStatus(OrderStatus.valueOf(status));
    }

    @Override
    public OrdersDTO getAll() {
        OrdersDTO ordersDTO = new OrdersDTO();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = orderMapper.getAllOrders();

        for (Order order: orders) {

            OrderDTO orderDTO = new OrderDTO();
            Long orderId = order.getId();
            orderDTO.setId(orderId);
            log.info("Order id " + order.getId());
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setName(order.getRestaurant().getName());
            orderDTO.setRestaurant(restaurantDTO);
            orderDTO.setTimestamp(order.getTimestamp());
            List<OrderItem> orderItems = orderItemMapper.getByOrderId(orderId);
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

    @Transactional
    @Override
    public OrderCreationResponseDTO createOrder(Long customerId, OrderCreationDTO orderCreationDTO) {
        long restaurantId = orderCreationDTO.getRestaurantId();
        Restaurant restaurant = restaurantService.getById(restaurantId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setCourier(null);
        order.setStatus(OrderStatus.CUSTOMER_CREATED);
        order.setTimestamp(LocalDateTime.now());
        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemForOrderCreationDTO menu: orderCreationDTO.getMenuItems()) {
             OrderItem orderItem = new OrderItem();
             orderItem.setOrder(order);
             RestaurantMenuItem restaurantMenuItem = restaurantMenuRepository.findById(menu.getMenuItemId()).orElseThrow(() -> new RestaurantMenuException("Menu not found"));
             orderItem.setRestaurantMenuItem(restaurantMenuItem);
             orderItem.setQuantity(menu.getQuantity());
             orderItem.setPrice(restaurantMenuItem.getPrice() * menu.getQuantity());
             orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);

        OrderCreationResponseDTO responseDTO = new OrderCreationResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setUrl("https://securepay.tinkoff.ru/v2/Submit3DSAuthorization");
        responseDTO.setTime(LocalDateTime.now().plusHours(1));

        return responseDTO;
    }
}
