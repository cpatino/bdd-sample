package com.example.calculator.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class Division {

    private static final String REGEX_PATTERN = "(\\d+[.\\d]*)([/])(\\d+[.\\d]*)";
    private static final Logger LOG = LoggerFactory.getLogger(Division.class.getName());

    public Number apply(String mathOperation) {
        LOG.info("A new operation has to be solved: {}", mathOperation);
        var pattern = Pattern.compile(REGEX_PATTERN);
        var matcher = pattern.matcher(mathOperation);
        if (matcher.find()) {
            var divisor = toDoubleValueOrZero(matcher.group(3));
            if (divisor == 0) {
                throw new UnsupportedOperationException("Cannot divide by zero");
            }
            var result = toDoubleValueOrZero(matcher.group(1)) / divisor;
            LOG.info("The result is {}, operation has been solved", result);
            return result;
        }
        throw new UnsupportedOperationException("The " + mathOperation + " is not a division");
    }

    private Double toDoubleValueOrZero(String number) {
        return Optional.ofNullable(number)
                .map(Double::parseDouble)
                .orElse(0.0);
    }
}
