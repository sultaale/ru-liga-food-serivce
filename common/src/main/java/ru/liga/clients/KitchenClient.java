package ru.liga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.StatusUpdateDTO;
import ru.liga.models.Courier;


@FeignClient(name = "kitchen-client", url = "http://localhost:8083")
public interface KitchenClient {

    @GetMapping(value = "/couriers/status/{status}")
    CourierDTO getByStatus(@PathVariable String status);
}
