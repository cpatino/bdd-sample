package com.example.calculator.adapter.outbound.rest.dto;

import java.time.Instant;

public class ErrorResponseDTO {

    private Instant timestamp;
    private String path;
    private int status;
    private String error;
    private String message;
    private String requestId;

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }
}
