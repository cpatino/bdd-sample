package com.example.calculator.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"single", "default"})
class Subtraction implements Operation {

    @Override
    public boolean shouldApply(String operator) {
        return operator.equals("-");
    }

    @Override
    public Number apply(Double number1, Double number2) {
        return number1 - number2;
    }
}
