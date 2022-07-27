package com.example.calculator.adapter.outbound.rest;

import com.example.calculator.adapter.outbound.rest.mapper.ErrorResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Profile("multi-service")
class AdditionWebClient extends CalculatorWebClient {

    private static final String SERVICE_URL = "http://localhost:8087/math/addition/";
    private static final Logger LOG = LoggerFactory.getLogger(AdditionWebClient.class.getName());

    AdditionWebClient(WebClient webClient, ErrorResponseMapper errorResponseMapper) {
        super(webClient, errorResponseMapper);
    }

    @Override
    public boolean shouldApply(String operator) {
        return operator.equals("+");
    }

    @Override
    public Number apply(String operation) {
        LOG.info("Calling the addition-service to resolve the {} operation", operation);
        return apply(SERVICE_URL, operation);
    }
}
