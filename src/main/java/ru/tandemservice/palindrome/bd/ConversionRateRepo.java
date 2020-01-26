package ru.tandemservice.palindrome.bd;

import javafx.util.Pair;

import java.util.Date;

/**
 * Интерфейс, используется для быстрой подмены репозитория (например если нужно хранить в bd)
 * @author Smagin-KV
 */
public interface ConversionRateRepo {
    Pair<Integer, Integer> getConversion(Date date);

    Pair<Integer, Integer> addConversionRate(int numLetter, int numPoint);
}
