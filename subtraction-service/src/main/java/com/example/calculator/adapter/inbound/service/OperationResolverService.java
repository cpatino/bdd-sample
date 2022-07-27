package com.example.calculator.adapter.inbound.service;

import com.example.calculator.domain.Subtraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Service
public class OperationResolverService {

    private static final Logger LOG = LoggerFactory.getLogger(OperationResolverService.class.getName());

    private final Subtraction subtraction;

    public OperationResolverService(Subtraction subtraction) {
        this.subtraction = subtraction;
    }

    public CompletableFuture<Number> resolve(String mathOperation) {
        return CompletableFuture.supplyAsync(() -> subtraction.apply(mathOperation))
                .exceptionally(ex -> {
                    var th = ex.getCause();
                    if (th instanceof UnsupportedOperationException) {
                        LOG.error(th.getMessage(), th);
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, th.getMessage(), th);
                    }
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, th.getMessage(), th);
                });
    }
}
