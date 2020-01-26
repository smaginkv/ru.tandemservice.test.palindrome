package ru.tandemservice.palindrome.bd.impl;

import javafx.util.Pair;
import ru.tandemservice.palindrome.bd.ConversionRateRepo;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ConversionRateRamImpl implements ConversionRateRepo {
    TreeMap<Date, Pair<Integer, Integer>> conversions = new TreeMap<>();

    @Override
    public Pair<Integer, Integer> getConversion(Date date) {
        Map.Entry<Date, Pair<Integer, Integer>> datePairEntry = conversions.floorEntry(new Date());
        if (null == datePairEntry) {
            return null;
        }
        return datePairEntry.getValue();
    }

    @Override
    public Pair<Integer, Integer> addConversionRate(int numLetter, int numPoint) {
        Pair<Integer, Integer> conversionRate = new Pair<>(numLetter, numPoint);
        conversions.put(new Date(), conversionRate);
        return conversionRate;
    }
}
