package com.example.calculator.adapter.outbound.rest.mapper;

import com.example.calculator.adapter.outbound.rest.dto.ErrorResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("multi-service")
public class ErrorResponseMapper {

    private final ObjectMapper objectMapper;

    public ErrorResponseMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ErrorResponseDTO map(String response) throws JsonProcessingException {
        return objectMapper.readValue(response, ErrorResponseDTO.class);
    }
}
