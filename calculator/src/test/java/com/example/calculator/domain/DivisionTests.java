package com.example.calculator.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = Division.class)
class DivisionTests {

    @Autowired
    private Division operation;

    @Test
    void whenShouldApply_thenCheckResult() {
        assertThat(operation.shouldApply("/")).isTrue();
    }

    @Test
    void whenApply_thenCalculate() {
        var result = operation.apply(6.0, 3.0);
        assertThat(result).isEqualTo(2.0);
    }

    @Test
    void givenDivisionByZero_whenApply_thenUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> operation.apply(6.0, 0.0));
    }
}
