package com.example.calculator.adapter.inbound;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class OperationResolverControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenSomeOperations_whenResolve_thenCheckResults() {
        var url = "http://localhost:" + port + "/math/addition";
        assertThat(restTemplate.postForObject(url, "1.0+3", Number.class)).isEqualTo(4.0);
        assertThat(restTemplate.postForObject(url, "0+3", Number.class)).isEqualTo(3.0);
    }

    @Test
    void givenAnyDifferentOperation_whenResolve_thenBadRequest() {
        var url = "http://localhost:" + port + "/math/addition";
        var response = restTemplate.postForObject(url, "1.0/3", String.class);
        assertThat(response).contains("400").contains("Bad Request").contains("is not an addition");
    }
}