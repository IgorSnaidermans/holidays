package com.snaidermans.holidays.validation.rule;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class CountryFormatRule implements ValidationRule {

    @Override
    public String getMessage() {
        return "Invalid country code. Format must be 'ISO 3166-1 alpha-2'";
    }

    @Override
    public List<String> validate(String country) {
        String[] isoCountries = Locale.getISOCountries();
        if (Arrays.stream(isoCountries).noneMatch(iso -> iso.equalsIgnoreCase(country))) {
            return Collections.singletonList(getMessage());
        }
        return Collections.emptyList();
    }

}
