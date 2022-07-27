package com.example.calculator.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Subtraction.class)
class SubtractionTests {

    @Autowired
    private Subtraction operation;

    @Test
    void whenShouldApply_thenCheckResult() {
        assertThat(operation.shouldApply("-")).isTrue();
    }

    @Test
    void whenApply_thenCalculate() {
        var result = operation.apply(1.0, 1.0);
        assertThat(result).isEqualTo(0.0);
    }
}
