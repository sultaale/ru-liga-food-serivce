package ru.liga.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.entity.Courier;
import ru.liga.entity.Order;
import ru.liga.entity.OrderItem;
import ru.liga.entity.Restaurant;
import ru.liga.entity.enums.OrderStatus;
import ru.liga.exceptions.DeliveryException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.exceptions.StatusNotFoundException;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderItemRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.service.CourierService;
import ru.liga.utils.DistanceCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrdersDTO getAll() {
    OrdersDTO ordersDTO = new OrdersDTO();
    List<OrderDTO> orderDTOS = new ArrayList<>();
    List<Order> orders = orderRepository.findAllByStatus(OrderStatus.DELIVERY_PICKING);

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
    public void acceptOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order not found"));
        Map<Long, Double> setOfDistance = new HashMap<>();
        Restaurant restaurant = order.getRestaurant();
        List<Courier> couriers = courierRepository.findAllByStatus(OrderStatus.DELIVERY_PENDING.toString());

        for (Courier courier: couriers) {
            Long courierId = courier.getId();
            double distance = DistanceCalculator.calculateDistance(courier.getCoordinates(), restaurant.getAddress());
            log.info("Courier with id " + id + " distance " + distance);
            setOfDistance.put(courierId, distance);
        }
        if (setOfDistance.isEmpty()) {
            log.warn("It seems all couriers are busy now");
        }
        Optional<Map.Entry<Long,Double>> courierId =
                setOfDistance.entrySet().stream()
                        .min(Map.Entry.comparingByValue());
        Courier courier = courierRepository.findById(courierId.get().getKey()).orElseThrow();


        order.setCourier(courier);
        order.setStatus(OrderStatus.DELIVERY_PICKING);
        courier.setStatus(OrderStatus.DELIVERY_PICKING.toString());
        courierRepository.save(courier);
        orderRepository.save(order);

    }

    public void completeOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow();
        Courier courier = order.getCourier();
        courier.setStatus(OrderStatus.DELIVERY_COMPLETE.toString());
        order.setStatus(OrderStatus.DELIVERY_COMPLETE);
        orderRepository.save(order);
        courierRepository.save(courier);

    }

    public OrderStatus convertToOrderStatus(String status) {
        return Arrays.stream(OrderStatus.values())
                .filter(s->s.toString().equalsIgnoreCase(status)).findAny()
                .orElseThrow(() -> new StatusNotFoundException("Provided status is not valid"));
    }

}
