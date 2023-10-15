package ru.liga.util;

import org.modelmapper.ModelMapper;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemDTO;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.RestaurantMenuItemInOrderItemDTO;
import ru.liga.models.Order;
import ru.liga.models.OrderItem;
import ru.liga.models.Restaurant;
import ru.liga.models.RestaurantMenuItem;

import java.util.List;
import java.util.stream.Collectors;


public class Converter {
    private final ModelMapper modelMapper;

    public Converter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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



    

}
