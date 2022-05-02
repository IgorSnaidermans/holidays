package com.snaidermans.holidays.service;

import com.snaidermans.holidays.Holiday;
import com.snaidermans.holidays.validation.ValidationService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HolidayServiceTest {

    public static final String COUNTRY = "lv";

    public static final String YEAR = "2020";

    @InjectMocks
    private HolidayService victim;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ValidationService validationService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        Holiday[] holidays = Arrays.asList(createHoliday("2020-01-01"), createHoliday("2020-05-01"))
                .toArray(new Holiday[2]);
        when(restTemplate.getForObject("https://date.nager.at/Api/v2/PublicHolidays/" + YEAR + "/"
                + COUNTRY, Holiday[].class)).thenReturn(holidays);
    }

    @Test
    public void shouldReturnTrueIfItsHoliday() {
        assertThat(victim.isHoliday(COUNTRY, "01012020"), is("yes"));
        assertThat(victim.isHoliday(COUNTRY, "01052020"), is("yes"));
    }

    @Test
    public void shouldFalseTrueIfItsNotHoliday() {
        assertThat(victim.isHoliday(COUNTRY, "01122020"), is("no"));
    }

    @Test
    public void shouldReturnHolidayCountIfOnlyYearSubmitted() {
        assertThat(victim.isHoliday(COUNTRY, YEAR), is("2"));
    }

    @Test
    public void shouldThrowExceptionIfNoHolidaysFound() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("No holidays found");
        victim.isHoliday("uk", "2999");
    }

    @Test
    public void shouldNotProceedIfValidationFailed() {
        doThrow(IllegalArgumentException.class).when(validationService).validate(any());
        try {
            victim.isHoliday("", "");
        } catch (IllegalArgumentException ignored) {
        }
        verify(restTemplate, never()).getForObject(any(), any());
    }

    private Holiday createHoliday(String date) {
        Holiday holiday = new Holiday();
        holiday.setDate(date);
        return holiday;
    }

}