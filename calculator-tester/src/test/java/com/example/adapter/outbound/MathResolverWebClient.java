package com.example.adapter.outbound;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class MathResolverWebClient extends CalculatorWebClient {

    private static final String SERVICE_URL = "http://localhost:8086/math/";

    MathResolverWebClient(WebClient webClient) {
        super(webClient);
    }

    public ResponseEntity<String> resolve(String mathOperation) {
        return performPost(SERVICE_URL, mathOperation);
    }
}
