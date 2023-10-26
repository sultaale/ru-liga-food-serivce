package ru.liga;

import org.apache.ibatis.session.SqlSessionFactory;
import org.modelmapper.ModelMapper;
import org.postgresql.core.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.liga.util.Converter;

import java.sql.Connection;


@SpringBootApplication
public class OrderService {

	public static void main(String[] args) {
		SpringApplication.run(OrderService.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Converter converter() {return new Converter(modelMapper());}

	
}
