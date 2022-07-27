package com.example.adapter.outbound;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

abstract class CalculatorWebClient {

    private final WebClient webClient;

    CalculatorWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    protected ResponseEntity<String> performPost(String serviceUrl, String request) {
        return webClient.post()
                .uri(serviceUrl)
                .bodyValue(request)
                .exchangeToMono(response -> response.toEntity(String.class))
                .retry(1L)
                .blockOptional()
                .orElseThrow(() -> new RuntimeException("Something went wrong and the ResponseEntity is not available"));
    }
}