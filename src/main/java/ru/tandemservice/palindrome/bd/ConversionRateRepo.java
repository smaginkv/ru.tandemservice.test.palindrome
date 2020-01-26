package ru.tandemservice.palindrome.bd;

import javafx.util.Pair;

import java.util.Date;

public interface ConversionRateRepo {
    Pair<Integer, Integer> getConversion(Date date);

    void addConversionRate(int numLetter, int numPoint);
}
