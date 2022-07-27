package com.example.calculator.adapter.outbound.rest;

import com.example.calculator.adapter.outbound.rest.mapper.ErrorResponseMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

abstract class CalculatorWebClient implements Operation {

    private final WebClient webClient;
    private final ErrorResponseMapper errorResponseMapper;

    CalculatorWebClient(WebClient webClient, ErrorResponseMapper errorResponseMapper) {
        this.webClient = webClient;
        this.errorResponseMapper = errorResponseMapper;
    }

    protected Number apply(String serviceUrl, String operation) {
        return transformResponseEntity(performPost(serviceUrl, operation));
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

    protected double transformResponseEntity(ResponseEntity<String> response) {
        if (response.getStatusCode().is2xxSuccessful() && response.hasBody() && Objects.nonNull(response.getBody())) {
            return Double.parseDouble(response.getBody());
        }
        var responseBody = response.getBody();
        try {
            var responseDTO = errorResponseMapper.map(responseBody);
            var status = Optional.of(responseDTO.getStatus())
                    .map(HttpStatus::resolve)
                    .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new ResponseStatusException(status, responseDTO.getMessage());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(responseBody, ex);
        }
    }
}