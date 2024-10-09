package com.codigoCerto.tarefas.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customSwagger(){
        return new OpenAPI().info(new Info().title("TRILHA BACK-END CODIGO CERTO")
                .version("1.0.0")
                .description("CRUD de tarefas com autenticação de usuários (ADMIN e USER)"));
    }
}
