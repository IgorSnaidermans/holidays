package com.snaidermans.holidays.validation.rule;

import java.util.List;

public interface ValidationRule {

    String getMessage();

    List<String> validate(String country);
}
