package com.snaidermans.holidays;

import com.snaidermans.holidays.mapping.DateParser;

import java.time.LocalDate;

public class Holiday {

    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = DateParser.HOLIDAY_DATE_FORMAT.parse(date);
    }
}
