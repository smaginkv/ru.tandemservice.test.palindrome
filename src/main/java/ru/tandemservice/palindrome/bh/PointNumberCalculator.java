package ru.tandemservice.palindrome.bh;

import javafx.util.Pair;

/**
 * Начисляет баллы за палиндром, в соответствии с текущим курсом
 * @author Smagin-KV
 */
public class PointNumberCalculator {
    public int calculate(Pair<Integer, Integer> pair, int phraseLength) {
        @SuppressWarnings("IntegerDivisionInFloatingPointContext")
        float pointsNumber = phraseLength*pair.getValue()/pair.getKey();
        return (int)pointsNumber;
    }
}
