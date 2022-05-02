package com.snaidermans.holidays.validation;

import com.snaidermans.holidays.validation.rule.CountryFormatRule;
import com.snaidermans.holidays.validation.rule.ValidationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
public class ValidationService {

    @Autowired
    private CountryFormatRule countryFormatRule;

    private final List<ValidationRule> rules = new ArrayList<>();

    @PostConstruct
    public void init() {
        rules.add(countryFormatRule);
    }

    public void validate(String countryCode) {
        List<String> errors = new ArrayList<>();
        rules.forEach(rule -> errors.addAll(rule.validate(countryCode)));
        if (!errors.isEmpty()) {
            StringJoiner messageJoiner = new StringJoiner(", ");
            errors.forEach(messageJoiner::add);
            throw new IllegalArgumentException(messageJoiner.toString());
        }
    }
}
