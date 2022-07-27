package com.example.calculator.adapter.inbound.service;

import com.example.calculator.domain.MathResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.CompletableFuture;

@Service
public class MathResolverService {

    private static final Logger LOG = LoggerFactory.getLogger(MathResolverService.class.getName());

    private final MathResolver mathResolver;

    public MathResolverService(MathResolver mathResolver) {
        this.mathResolver = mathResolver;
    }

    public CompletableFuture<Number> resolve(String mathOperation) {
        return CompletableFuture.supplyAsync(() -> mathResolver.applyOperation(mathOperation))
                .exceptionally(ex -> {
                    var th = ex.getCause();
                    if (th instanceof UnsupportedOperationException) {
                        LOG.error(th.getMessage(), th);
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, th.getMessage(), th);
                    }

                    if (th instanceof ResponseStatusException) {
                        LOG.error(th.getMessage(), th);
                        throw (ResponseStatusException) th;
                    }

                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, th.getMessage(), th);
                });
    }
}
