package com.example.calculator.adapter.inbound;

import com.example.calculator.adapter.inbound.service.MathResolverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/math")
@RestController
class MathResolverController {

    private final MathResolverService mathResolverService;

    protected MathResolverController(MathResolverService mathResolverService) {
        this.mathResolverService = mathResolverService;
    }

    @PostMapping
    public CompletableFuture<Number> resolve(@RequestBody String mathOperation) {
        return mathResolverService.resolve(mathOperation);
    }
}
