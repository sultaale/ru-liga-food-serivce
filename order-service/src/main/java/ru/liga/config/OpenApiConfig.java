package ru.liga.config;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
     public OpenApiCustomiser openApiCustomiser() {
         return openApi -> {
             openApi.addSecurityItem(new SecurityRequirement().addList("oath2 authentication"))
                     .components(new Components().addSecuritySchemes("oath2 authentication", createAPIKeyScheme()
                             .flows(new OAuthFlows().clientCredentials(new OAuthFlow().tokenUrl("http://localhost:9000" + "/oauth/token")
                                     .scopes(new Scopes().addString("read", "for reading").addString("write", "for writing"))))));
             Info info = new Info();
             info.setTitle("API сервиса заказа");
             info.setDescription("Сервис заказа предоставляет возможность управления заказом");
             openApi.info(info);
         };
     }

     private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.OAUTH2).bearerFormat("JWT").scheme("oath2");
     }
}
