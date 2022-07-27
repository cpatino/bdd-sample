package com.example.calculator.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {MathResolverImpl.class, Addition.class, Subtraction.class, Multiplication.class, Division.class})
class MathResolverTest {

    @Autowired
    private MathResolverImpl mathResolverImpl;

    @Test
    void whenApplyOperation_thenCheckOperationsWereAppliedLeftToRight() {
        assertThat(mathResolverImpl.applyOperation("1.0+3-2*8/4.0")).isEqualTo(4.0);
        assertThat(mathResolverImpl.applyOperation("5*2-1/3")).isEqualTo(3.0);
    }

    @Test
    void givenIncompleteOperations_whenApplyOperation_thenCheckOperationsWereAppliedLeftToRight() {
        assertThat(mathResolverImpl.applyOperation("+5-2*8/4.0")).isEqualTo(6.0);
        assertThat(mathResolverImpl.applyOperation("5-2*8/4.0+")).isEqualTo(6.0);
    }

    @Test
    void givenDivisionByZero_whenApplyOperation_thenUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> mathResolverImpl.applyOperation("5-2*8/0"));
    }
}