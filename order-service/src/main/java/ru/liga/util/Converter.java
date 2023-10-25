package ru.liga.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.CustomerDTO;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.RestaurantMenuItemInOrderItemDTO;
import ru.liga.models.Courier;
import ru.liga.models.Customer;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;

    public CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    
    public OrderDTO toDTO(Order order) {

        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        RestaurantDTO restaurantDTO = modelMapper.map(order.getRestaurant(), RestaurantDTO.class);

        List<OrderItemDTO> orderItemDTO = order.getItems().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        orderDTO.setRestaurant(restaurantDTO);
        orderDTO.setItems(orderItemDTO);
        return orderDTO;
    }

    public OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);

        RestaurantMenuItemInOrderItemDTO menuItemForOrderItemDTO =
                modelMapper.map(orderItem.getRestaurantMenuItem(), RestaurantMenuItemInOrderItemDTO.class);

        orderItemDTO.setDescription(menuItemForOrderItemDTO.getDescription());
        orderItemDTO.setImage(menuItemForOrderItemDTO.getImage());
        return orderItemDTO;
    }

    public OrderItem toEntity(OrderCreationDTO orderCreationDTO) {

        OrderItem orderItem = new OrderItem();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(orderCreationDTO.getRestaurantId());
        RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem();
        restaurantMenuItem.setId(orderCreationDTO.getRestaurantId());
        orderItem.setQuantity(orderCreationDTO.getOrderItems().getQuantity());
        orderItem.setRestaurantMenuItem(restaurantMenuItem);

         return orderItem;
    }

    public CourierDTO toDTO(Courier courier) {
        return modelMapper.map(courier, CourierDTO.class);
    }

     public Courier toEntity(CourierDTO courierDTO) {
        Courier courier = new Courier();
        courier.setId(courierDTO.getId());
        courier.setStatus(courierDTO.getStatus());
        courier.setCoordinates(courierDTO.getCoordinates());
        return courier;
     }
}
