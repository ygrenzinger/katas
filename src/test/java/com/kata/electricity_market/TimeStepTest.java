package com.kata.electricity_market;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.kata.electricity_market.TimeStep.HALFHOUR;
import static com.kata.electricity_market.TimeStep.HOUR;
import static com.kata.electricity_market.TimeStep.QUARTERHOUR;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeStepTest {

    @Test
    public void convertFromHourToHalfHour() {
        Map<LocalDateTime, BigDecimal> puissanceByTimeStep = new HashMap<>();
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 12, 0, 0), BigDecimal.ONE);
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 13, 0, 0), BigDecimal.ONE);

        Map<LocalDateTime, BigDecimal> result = HOUR.convertTo(puissanceByTimeStep, HALFHOUR);

        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 0, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 30, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 0, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 30, 0), BigDecimal.ONE);

    }

    @Test
    public void convertFromHourToQuarterHour() {
        Map<LocalDateTime, BigDecimal> puissanceByTimeStep = new HashMap<>();
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 12, 0, 0), BigDecimal.ONE);
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 13, 0, 0), BigDecimal.TEN);

        Map<LocalDateTime, BigDecimal> result = HOUR.convertTo(puissanceByTimeStep, QUARTERHOUR);

        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 0, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 15, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 30, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 45, 0), BigDecimal.ONE);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 0, 0), BigDecimal.TEN);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 15, 0), BigDecimal.TEN);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 30, 0), BigDecimal.TEN);
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 45, 0), BigDecimal.TEN);

    }

    @Test
    public void convertFromHalfHourToHour() {

        Map<LocalDateTime, BigDecimal> puissanceByTimeStep = new HashMap<>();
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 12, 0, 0), BigDecimal.TEN);
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 12, 30, 0), BigDecimal.TEN);
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 13, 0, 0), BigDecimal.valueOf(30));
        puissanceByTimeStep.put(LocalDateTime.of(2015, 12, 5, 13, 30, 0), BigDecimal.valueOf(30));

        Map<LocalDateTime, BigDecimal> result = HALFHOUR.convertTo(puissanceByTimeStep, HOUR);

        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 12, 0, 0), BigDecimal.valueOf(20));
        assertThat(result).containsEntry(LocalDateTime.of(2015, 12, 5, 13, 0, 0), BigDecimal.valueOf(20));
    }
}
