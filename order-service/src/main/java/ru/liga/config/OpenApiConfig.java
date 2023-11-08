package ru.liga.config;



import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
     public OpenApiCustomiser openApiCustomiser() {
         return openApi -> {
             Info info = new Info();
             info.setTitle("API сервиса ресторана");
             info.setDescription("Сервис ресторана предоставляет возможность управления заказом");
             openApi.info(info);
         };
     }
}
