package com.example.rideservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class DriverClient {

    private final WebClient webClient;

    public DriverClient(@Qualifier("driverServiceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<DriverDto> getAllDrivers() {
        Mono<List<DriverDto>> responseMono = webClient.get()
                .uri("/drivers")
                .retrieve()
                .bodyToMono(new org.springframework.core.ParameterizedTypeReference<List<DriverDto>>() {
                });

        return responseMono.block();
    }

    public java.util.Optional<DriverDto> findFirstAvailableDriver() {
        return getAllDrivers().stream()
                .filter(driver -> "AVAILABLE".equals(driver.getStatus()))
                .findFirst();
    }

    public void markDriverAsBusy(Long driverId) {
        webClient.patch()
                .uri("/drivers/{id}/status", driverId)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue("\"BUSY\"")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}