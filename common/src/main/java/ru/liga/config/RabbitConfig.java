package ru.liga.config;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableRabbit
public class RabbitConfig {

        @Bean
        public CachingConnectionFactory connectionFactory() {
            CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
            cachingConnectionFactory.setUsername("guest");
            cachingConnectionFactory.setPassword("guest");
            return cachingConnectionFactory;
        }

        @Bean
        public AmqpAdmin amqpAdmin() {
            return new RabbitAdmin(connectionFactory());
        }

        @Bean
        public RabbitTemplate rabbitTemplate() {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            return new RabbitTemplate(connectionFactory());
        }
}

