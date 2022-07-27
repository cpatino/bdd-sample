package com.example.domain.model;

import org.springframework.http.ResponseEntity;

import java.util.Objects;

public record MathOperation(String operation, ResponseEntity<String> response) {

    public MathOperation {
        Objects.requireNonNull(operation);
    }

    public MathOperation(String mathOperation) {
        this(mathOperation, null);
    }
}
