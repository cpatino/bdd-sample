package com.example.calculator.adapter.inbound;

import com.example.calculator.adapter.inbound.service.OperationResolverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/math/multiplication")
@RestController
class OperationResolverController {

    private final OperationResolverService operationResolverService;

    protected OperationResolverController(OperationResolverService operationResolverService) {
        this.operationResolverService = operationResolverService;
    }

    @PostMapping
    public CompletableFuture<Number> resolve(@RequestBody String mathOperation) {
        return operationResolverService.resolve(mathOperation);
    }
}
