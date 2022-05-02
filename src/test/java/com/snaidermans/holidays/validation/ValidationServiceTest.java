package com.snaidermans.holidays.validation;

import com.snaidermans.holidays.validation.rule.CountryFormatRule;
import com.snaidermans.holidays.validation.rule.ValidationRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {
    @InjectMocks
    private ValidationService victim;

    @Mock
    private CountryFormatRule countryFormatRule;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        victim.init();
    }

    @Test
    public void shouldContainRules() {
        List<ValidationRule> rules = Whitebox.getInternalState(victim, "rules");
        assertThat(rules, containsInAnyOrder(countryFormatRule));
    }

    @Test
    public void shouldThrowExceptionIfValidationFails() {
        Mockito.when(countryFormatRule.validate("lav")).thenReturn(Arrays.asList("error0", "error1"));
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("error0, error1");
        victim.validate("lav");
    }
}