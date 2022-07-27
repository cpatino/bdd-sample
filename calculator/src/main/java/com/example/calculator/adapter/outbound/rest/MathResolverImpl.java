package com.example.calculator.adapter.outbound.rest;

import com.example.calculator.domain.PatternMatcherMathResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Matcher;

@Service
@Profile("multi-service")
class MathResolverImpl extends PatternMatcherMathResolver {

    private static final Logger LOG = LoggerFactory.getLogger(MathResolverImpl.class.getName());

    private final Set<Operation> operations;

    public MathResolverImpl(Set<Operation> operations) {
        this.operations = operations;
    }

    @Override
    protected Number applyOperation(Matcher matcher) {
        var mathOperation = matcher.group(0);
        LOG.info("Performing {} operation", mathOperation);
        var operationSymbol = matcher.group(2);

        return operations.parallelStream()
                .filter(operation -> operation.shouldApply(operationSymbol))
                .findFirst()
                .map(operation -> operation.apply(mathOperation))
                .orElseThrow(() -> new RuntimeException("Operation is not supported"));
    }
}
