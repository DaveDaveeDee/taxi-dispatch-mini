package com.example.rideservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient driverServiceWebClient(@Value("${driver-service.url}") String driverServiceUrl) {
        return WebClient.builder()
                .baseUrl(driverServiceUrl)
                .build();
    }
}