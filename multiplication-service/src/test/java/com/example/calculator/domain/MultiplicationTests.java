package com.example.calculator.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = Multiplication.class)
class MultiplicationTests {

    @Autowired
    private Multiplication operation;

    @Test
    void whenApply_thenCalculate() {
        var result = operation.apply("3.0*4");
        assertThat(result).isEqualTo(12.0);
    }

    @Test
    void givenADifferentOperation_whenApply_thenUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> operation.apply("1-1"));
    }
}
