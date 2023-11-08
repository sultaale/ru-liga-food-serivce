package ru.liga.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.batisMapper.OrderMapper;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.CustomerDTO;
import ru.liga.dto.DeliveriesDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.exceptions.CourierNotFoundException;
import ru.liga.exceptions.DeliveryException;
import ru.liga.exceptions.OrderNotFoundException;
import ru.liga.exceptions.StatusIsNotValidException;
import ru.liga.models.Courier;

import ru.liga.models.Customer;
import ru.liga.models.Order;
import ru.liga.models.Restaurant;
import ru.liga.models.enums.OrderStatus;
import ru.liga.repository.CourierRepository;
import ru.liga.repository.OrderRepository;
import ru.liga.services.CourierService;
import ru.liga.utils.Converter;
import ru.liga.utils.DistanceCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final Converter converter;


    @Override
    public CourierDTO getById(Long id) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found by id " + id));
        return converter.toDTO(courier);
    }

    @Override
    public CourierDTO getByPhone(String phone) {
       Courier courier = courierRepository.findByPhone(phone)
               .orElseThrow(() -> new CourierNotFoundException("Courier not found"));
       return converter.toDTO(courier);
    }

    @Override
    public List<CourierDTO> getAllCouriers() {
        return courierRepository.findAll().stream()
                .map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public CourierDTO findByStatus(String status) {
        Courier courier = courierRepository.findFirstByStatus(status).orElseThrow();
        return converter.toDTO(courier);
    }

    @Transactional
    @Override
    public Order updateStatus(Long id, StatusUpdateDTO statusUpdateDTO) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found"));

        Long orderId = statusUpdateDTO.getId();
        OrderStatus orderStatus = convertToOrderStatus(statusUpdateDTO.getStatus());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));

        if (orderStatus.equals(OrderStatus.DELIVERY_PENDING)){
             courier.setStatus(OrderStatus.DELIVERY_PICKING.toString());
             order.setStatus(OrderStatus.DELIVERY_PICKING);
             order.setCourier(courier);
             orderRepository.save(order);
             courierRepository.save(courier);
        }
        return order;
    }

    @Transactional
    @Override
    public void acceptOrder(StatusUpdateDTO statusUpdateDTO) {
        Long orderId = statusUpdateDTO.getId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));
        
        OrderStatus orderStatus = convertToOrderStatus(statusUpdateDTO.getStatus());
        order.setStatus(orderStatus);

        orderRepository.save(order);
    }

    @Override
    public DeliveriesDTO findAllDeliveriesByStatus(String status) {
        OrderStatus orderStatus = convertToOrderStatus(status);
        DeliveriesDTO deliveriesDTO = new DeliveriesDTO();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByStatus(orderStatus);
         
        for (Order order: orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            Courier courier = order.getCourier();
            Restaurant restaurant = order.getRestaurant();
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setAddress(restaurant.getAddress());
            Customer customer = order.getCustomer();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setAddress(customer.getAddress());
            if (courier == null) {
                 restaurantDTO.setDistance(null);
                 customerDTO.setDistance(null);
                 orderDTO.setRestaurantDTO(restaurantDTO);
                 orderDTO.setCustomerDTO(customerDTO);
                 orderDTO.setPayment(0.00);
                 orderDTOS.add(orderDTO);
                 continue;
            }
                String courierCoordinates = courier.getCoordinates();
                double distanceRestaurant = DistanceCalculator.calculateDistance(courierCoordinates, restaurant.getAddress());
                restaurantDTO.setDistance(distanceRestaurant);
                double distanceCustomer = DistanceCalculator.calculateDistance(courierCoordinates, customer.getAddress());
                customerDTO.setDistance(distanceCustomer);
                orderDTO.setRestaurantDTO(restaurantDTO);
                orderDTO.setCustomerDTO(customerDTO);
                orderDTO.setPayment(200.00);
                orderDTOS.add(orderDTO);
        }
        deliveriesDTO.setOrders(orderDTOS);
        return deliveriesDTO;
    }

    public Courier findCourier(StatusUpdateDTO statusUpdateDTO) {
        List<Courier> couriers = courierRepository.findAllByStatus(OrderStatus.DELIVERY_PENDING.toString());
        Map<Long, Double> setOfDistance = new HashMap<>();
        Order order = orderRepository.findById(statusUpdateDTO.getId()).orElseThrow(()-> new OrderNotFoundException("order not found"));
        Restaurant restaurant = order.getRestaurant();

        for (Courier courier: couriers) {
            Long id = courier.getId();
            double distance = DistanceCalculator.calculateDistance(courier.getCoordinates(), restaurant.getAddress());
            log.info("Courier with id " + id + " distance " + distance);
            setOfDistance.put(id, distance);
        }

        if (setOfDistance.isEmpty()) {
            log.warn("It seems all couriers are busy now");
            throw new DeliveryException("Unfortunately, all couriers are busy now, please try later.");
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
        return courier;
    }

    public OrderStatus convertToOrderStatus(String status) {
       return Arrays.stream(OrderStatus.values())
               .filter(s->s.toString().equalsIgnoreCase(status)).findAny()
               .orElseThrow(() -> new StatusIsNotValidException("Provided status is not valid"));
    }
}
