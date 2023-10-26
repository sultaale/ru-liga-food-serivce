package ru.liga.service;


public interface KitchenService {

    void updateStatus(Long id);

    void completeOrder(Long orderId, String routingKey);
}
