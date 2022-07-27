package com.example.calculator.adapter.outbound.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Profile("multi-service")
class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
}
