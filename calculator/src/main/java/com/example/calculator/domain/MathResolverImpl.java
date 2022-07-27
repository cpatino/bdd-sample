package com.example.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

@Service
@Profile({"single", "default"})
class MathResolverImpl extends PatternMatcherMathResolver {

    private static final Logger LOG = LoggerFactory.getLogger(MathResolverImpl.class.getName());

    private final Set<Operation> operations;

    public MathResolverImpl(Set<Operation> operations) {
        this.operations = operations;
    }

    @Override
    protected Number applyOperation(Matcher matcher) {
        LOG.info("Performing {} operation", matcher.group(0));
        var number1 = toDoubleValueOrZero(matcher.group(1));
        var operationSymbol = matcher.group(2);
        var number2 = toDoubleValueOrZero(matcher.group(3));

        return operations.parallelStream()
                .filter(operation -> operation.shouldApply(operationSymbol))
                .findFirst()
                .map(operation -> operation.apply(number1, number2))
                .orElseThrow(() -> new RuntimeException("Operation is not supported"));
    }

    private Double toDoubleValueOrZero(String number) {
        return Optional.ofNullable(number)
                .map(Double::parseDouble)
                .orElse(0.0);
    }
}
