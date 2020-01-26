package ru.tandemservice.palindrome.bh;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tandemservice.palindrome.application.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointNumberCalculatorTest {
    private PointNumberCalculator pCalc;

    @BeforeEach
    void setUp() {
        pCalc = Context.getContext().getPointNumberCalculator();
    }

    @Test
    void shouldOkWhenSimpleCourse() {
        int value = pCalc.calculate(new Pair<>(1, 1), 10);
        assertEquals(value, 10);
    }

    @Test
    void shouldOkWhenComplexCourse() {
        int value = pCalc.calculate(new Pair<>(3, 2), 30);
        assertEquals(value, 20);
    }

    @Test
    void shouldOkWhenIrregularCourse() {
        int value = pCalc.calculate(new Pair<>(3, 2), 10);
        assertEquals(value, 6);
    }
}