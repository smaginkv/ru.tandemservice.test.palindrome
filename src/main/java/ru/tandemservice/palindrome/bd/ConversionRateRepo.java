package ru.tandemservice.palindrome.bd;

import javafx.util.Pair;

import java.util.Date;

public interface ConversionRateRepo {
    Pair<Integer, Integer> getConversion(Date date);

    Pair<Integer, Integer> addConversionRate(int numLetter, int numPoint);
}
