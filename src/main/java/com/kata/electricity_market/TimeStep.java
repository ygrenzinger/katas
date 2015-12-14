package com.kata.electricity_market;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public enum TimeStep {
    HOUR(4), HALFHOUR(2), QUARTERHOUR(1);

    public final int unit;

    TimeStep(int unit) {
        this.unit = unit;
    }

    public Map<LocalDateTime, BigDecimal> convertTo(Map<LocalDateTime, BigDecimal> puissanceByTimeStep, TimeStep timeStep) {
        if (this.unit > timeStep.unit) {
            return unfoldPuissance(puissanceByTimeStep, timeStep);
        } else if (this.unit < timeStep.unit) {
           return puissanceByTimeStep;
        } else {
            return puissanceByTimeStep;
        }
    }

    private Map<LocalDateTime, BigDecimal> unfoldPuissance(Map<LocalDateTime, BigDecimal> puissanceByTimeStep, TimeStep timeStep) {
        int foldBy = this.unit / timeStep.unit;
        return puissanceByTimeStep.entrySet().stream()
                .map(unfoldPuissance(timeStep, foldBy))
                .flatMap(s -> s)
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private Function<Map.Entry<LocalDateTime, BigDecimal>, Stream<AbstractMap.SimpleEntry<LocalDateTime, BigDecimal>>> unfoldPuissance(TimeStep timeStep, int foldBy) {
        return entry -> IntStream.range(0, foldBy)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(entry.getKey().plusMinutes(15 * timeStep.unit * i), entry.getValue()));
    }
}
