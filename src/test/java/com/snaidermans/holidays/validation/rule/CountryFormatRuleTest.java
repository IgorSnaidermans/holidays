package com.snaidermans.holidays.validation.rule;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CountryFormatRuleTest {

    CountryFormatRule victim = new CountryFormatRule();

    @Test
    public void shouldNotViolateValidCountry() {
        assertThat(victim.validate("lv"), empty());
    }

    @Test
    public void shouldViolateInvalidCountry() {
        List<String> result = victim.validate("hun");
        assertThat(result, hasSize(1));
        assertThat(result.get(0), is(victim.getMessage()));
    }

}