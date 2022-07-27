package com.example.calculator.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"single", "default"})
class Division implements Operation {

    @Override
    public boolean shouldApply(String operator) {
        return operator.equals("/");
    }

    @Override
    public Number apply(Double number1, Double number2) {
        if (number2 == 0) {
            throw new UnsupportedOperationException("Cannot divide by zero");
        }
        return number1 / number2;
    }
}
