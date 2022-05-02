package com.snaidermans.holidays.mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public enum DateParser {

    FULL_DATE_FORMAT(DateTimeFormatter.ofPattern("ddMMyyyy")),
    ONLY_YEAR_FORMAT(DateTimeFormatter.ofPattern("yyyy")),
    HOLIDAY_DATE_FORMAT(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private final DateTimeFormatter formatter;

    DateParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public LocalDate parse(String date) {
        return LocalDate.parse(date, formatter);
    }

    public boolean isAcceptableFormat(String date) {
        try {
            formatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
