package ru.tandemservice.palindrome.bd.impl;

import javafx.util.Pair;
import ru.tandemservice.palindrome.bd.ConversionRateRepo;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс - имплементация репозитория, организованного в памяти
 * репозиторий хранит в себе курс конвертации букв палиндрома в баллы на определенную дату
 * @author Smagin-KV
 */
public class ConversionRateRamImpl implements ConversionRateRepo {
    TreeMap<Date, Pair<Integer, Integer>> conversions = new TreeMap<>();

    /**
     * Получить курс из репозитория
     * @param date - дата на которую будет получен курс
     * @return - кортеж курса
     */
    @Override
    public Pair<Integer, Integer> getConversion(Date date) {
        Map.Entry<Date, Pair<Integer, Integer>> datePairEntry = conversions.floorEntry(new Date());
        if (null == datePairEntry) {
            return null;
        }
        return datePairEntry.getValue();
    }

    /**
     * Добавить курс в репозиторий
     * @param numLetter - за какое количество букв
     * @param numPoint - какое количество баллов назначается
     * @return - кортеж курса
     */
    @Override
    public Pair<Integer, Integer> addConversionRate(int numLetter, int numPoint) {
        Pair<Integer, Integer> conversionRate = new Pair<>(numLetter, numPoint);
        conversions.put(new Date(), conversionRate);
        return conversionRate;
    }
}
