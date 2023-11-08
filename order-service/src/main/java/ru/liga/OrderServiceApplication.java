package ru.liga;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.liga.utils.Converter;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);  }


        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public Converter converter() {return new Converter(modelMapper());}

}
