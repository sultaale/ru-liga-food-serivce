package ru.liga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.liga.dto.StatusUpdateDTO;



@FeignClient(name = "kitchen-client", url = "http://localhost:8083")
public interface KitchenClient {

    @PostMapping(value = "/api/v1/couriers/orders/status")
    void updateStatus(@RequestBody StatusUpdateDTO statusUpdateDTO) ;
}
