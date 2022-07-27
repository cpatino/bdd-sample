package com.example.calculator.adapter.inbound;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class MathResolverControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenSomeOperations_whenResolve_thenCheckResults() {
        var url = "http://localhost:" + port + "/math";
        assertThat(restTemplate.postForObject(url, "1.0+3-2*8/4.0", Number.class)).isEqualTo(4.0);
        assertThat(restTemplate.postForObject(url, "5*2-1/3", Number.class)).isEqualTo(3.0);
    }

    @Test
    void givenDivisionByZero_whenResolve_thenBadRequest() {
        var url = "http://localhost:" + port + "/math";
        var response = restTemplate.postForObject(url, "1.0+3-2*8/0", String.class);
        assertThat(response).contains("400").contains("Bad Request").contains("Cannot divide by zero");
    }
}
