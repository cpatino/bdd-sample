package com.example.calculator.domain;

interface Operation {

    boolean shouldApply(String operator);

    Number apply(Double number1, Double number2);
}
