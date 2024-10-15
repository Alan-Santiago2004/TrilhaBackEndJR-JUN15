package com.codigoCerto.tarefas.docs;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${APP_URL:http://localhost:8080}")
    private String appUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("TRILHA INICIAL CODIGO CERTO")
                        .version("v1"))
                // Adicione o servidor correto com HTTPS
                .addServersItem(new Server().url(appUrl));
    }
}
