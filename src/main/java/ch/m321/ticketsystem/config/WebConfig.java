package ch.m321.ticketsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Erlaubt CORS für alle Pfade
                .allowedOrigins("http://localhost:4200")  // Erlaubt nur localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Erlaubt die HTTP-Methoden
                .allowedHeaders("*");  // Erlaubt alle Header
    }
}
