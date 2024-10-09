package com.codigoCerto.tarefas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfigurations implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://trilhabackendjr-jun15-production-1e59.up.railway.app",
                        "http://localhost:8080",
                        "https://trilhabackendjr-jun15-production-1e59.up.railway.app/swagger-ui/index.html#/**"
                )
                .allowCredentials(true)
                .allowedMethods("*");
    }
}
