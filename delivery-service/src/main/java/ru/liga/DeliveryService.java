package ru.liga;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.liga.utils.Converter;

@SpringBootApplication
public class DeliveryService {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryService.class, args);
    }

    @Bean
    public ModelMapper modelMapperDelivery() {
        return new ModelMapper();
    }

    @Bean
    public Converter converterDelivery() {return new Converter(modelMapperDelivery());}
}
