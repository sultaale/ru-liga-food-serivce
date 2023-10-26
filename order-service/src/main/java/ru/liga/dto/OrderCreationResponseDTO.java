package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderCreationResponseDTO {

    private Long id;
    @JsonProperty("secret_payment_url")
    private String url;
    @JsonProperty("estimated_time_of_arrival")
    private LocalDateTime time;
}
