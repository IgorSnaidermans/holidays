package com.snaidermans.holidays.service;

import com.snaidermans.holidays.Holiday;
import com.snaidermans.holidays.mapping.DateParser;
import com.snaidermans.holidays.mapping.ResponseMapping;
import com.snaidermans.holidays.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.StringJoiner;

@Component
public class HolidayService {

    public static final String HOLIDAYS_URL = "https://date.nager.at/Api/v2/PublicHolidays";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ValidationService validationService;

    public String isHoliday(@NonNull String countryCode, @NonNull String date) {
        validationService.validate(countryCode);
        StringJoiner urlJoiner = new StringJoiner("/").add(HOLIDAYS_URL);
        if (DateParser.ONLY_YEAR_FORMAT.isAcceptableFormat(date)) {
            return getTotalHolidays(countryCode, date, urlJoiner);
        }
        return ResponseMapping.map(isHoliday(countryCode, date, urlJoiner));
    }

    private String getTotalHolidays(String countryCode, String date, StringJoiner urlJoiner) {
        Holiday[] holidays = getHolidays(urlJoiner, countryCode, date);
        return String.valueOf(holidays.length);
    }

    private boolean isHoliday(String countryCode, String date, StringJoiner urlJoiner) {
        LocalDate requestDate = DateParser.FULL_DATE_FORMAT.parse(date);
        Holiday[] holidays = getHolidays(urlJoiner, countryCode, String.valueOf(requestDate.getYear()));
        for (Holiday holiday : holidays) {
            if (holiday.getDate().equals(requestDate)) {
                return true;
            }
        }
        return false;
    }

    private Holiday[] getHolidays(StringJoiner urlJoiner, String countryCode, String requestDate) {
        urlJoiner.add(requestDate).add(countryCode);
        Holiday[] holidays = restTemplate.getForObject(urlJoiner.toString(), Holiday[].class);
        if (holidays == null) {
            throw new IllegalStateException("No holidays found");
        }
        return holidays;
    }

}
