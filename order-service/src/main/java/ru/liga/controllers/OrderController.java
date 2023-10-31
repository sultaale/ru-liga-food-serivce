package ru.liga.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.OrderCreationDTO;
import ru.liga.dto.OrderCreationResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.OrderItemForOrderCreationDTO;
import ru.liga.dto.OrdersDTO;
import ru.liga.services.OrderItemService;
import ru.liga.services.OrderService;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping ("/{customerId}")
    public OrderCreationResponseDTO createOrder(@PathVariable Long customerId, @RequestBody OrderCreationDTO orderCreationDTO) {
        return orderService.createOrder(customerId, orderCreationDTO);
    }

    @GetMapping
    public OrdersDTO getAll() {
        OrdersDTO ordersDTO = orderService.getAll();
        Pageable pageable1 = PageRequest.of(0,10);
        Page<OrderDTO> page = new PageImpl<OrderDTO>(ordersDTO.getOrders(), pageable1, ordersDTO.getOrders().size());
        return ordersDTO;
    }
    
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
          return orderService.getById(id);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteOrderItem(@PathVariable Long itemId) {
        orderItemService.deleteItem(itemId);
    }

    @PostMapping("/items/orders/{orderId}")
    public Long createOrderItem(@PathVariable Long orderId, @RequestBody OrderItemForOrderCreationDTO orderItemForOrderCreationDTO) {
        return orderItemService.addOrderItem(orderId, orderItemForOrderCreationDTO);
    }

}
