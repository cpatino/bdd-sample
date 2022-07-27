package com.example.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PatternMatcherMathResolver implements MathResolver {

    private static final String REGEX_PATTERN = "(\\d+[.\\d]*)([*/+-])(\\d+[.\\d]*)";
    private static final Logger LOG = LoggerFactory.getLogger(PatternMatcherMathResolver.class.getName());

    @Override
    public Number applyOperation(String mathOperation) {
        LOG.info("A new operation has to be solved: {}", mathOperation);
        var pattern = Pattern.compile(REGEX_PATTERN);
        var result = applyOperation(mathOperation, pattern);
        return Double.parseDouble(result.replaceAll("[*/+-]", ""));
    }

    private String applyOperation(String mathOperation, Pattern pattern) {
        var matcher = pattern.matcher(mathOperation);
        if (matcher.find()) {
            var result = applyOperation(matcher);
            return applyOperation(mathOperation.replaceFirst(REGEX_PATTERN, result.toString()), pattern);
        }
        LOG.info("The result is {}, operation has been solved", mathOperation);
        return mathOperation;
    }

    protected abstract Number applyOperation(Matcher matcher);
}
