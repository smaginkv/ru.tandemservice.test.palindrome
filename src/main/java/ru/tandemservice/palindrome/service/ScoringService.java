package ru.tandemservice.palindrome.service;

import javafx.util.Pair;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.bd.ConversionRateRepo;
import ru.tandemservice.palindrome.bd.PhraseRepo;
import ru.tandemservice.palindrome.bd.ScoringRepo;
import ru.tandemservice.palindrome.bh.PointNumberCalculator;
import ru.tandemservice.palindrome.entity.Phrase;
import ru.tandemservice.palindrome.entity.User;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * Класс, который получает данные о палиндромах которые были защитаны пользователю с репозиториев
 * и обрабатывает эти данные
 * @author Smagin-KV
 */
public class ScoringService {
    private ScoringRepo scoringRepo;
    private PhraseRepo phraseRepo;
    private ConversionRateRepo conversionRateRepo;
    private PointNumberCalculator pointCalc;

    public ScoringService(ScoringRepo scoringRepo, PhraseRepo phraseRepo, ConversionRateRepo conversionRateRepo,
                          PointNumberCalculator pointCalc) {
        this.scoringRepo = scoringRepo;
        this.phraseRepo = phraseRepo;
        this.conversionRateRepo = conversionRateRepo;
        this.pointCalc = pointCalc;
    }

    /**
     * Добавить палиндром пользователя в репозиторий и начислить баллы
     * @param user - текущий пользователь
     * @param value - фраза пользователя
     * @return - баллы начисленные за палиндром
     * @throws InvalidValue - Не корреткное значение пользователя или фразы
     */
    public int addUsersPhrase(User user, String value) throws InvalidValue {
        if (null == user) {
            throw new InvalidValue("User must not be equals null");
        }

        Phrase phrase = getPhrase(value);
        int pointNumber = getPointNumber(user, phrase);
        return scoringRepo.save(user, phrase, pointNumber);
    }

    /**
     * Получить доску лидеров
     * @param topPlayers - количество лидеров на доске
     * @return - список лидеров
     */
    public Set<Pair<User, BigInteger>> getLeaders(int topPlayers) {
        return scoringRepo.getTopLeaders(topPlayers);
    }

    /**
     * Рассчитать и вернуть число баллов за палиндром
     * @param user - текущий пользователь
     * @param phrase - тиекущая фраза
     * @return - количество балло
     * @throws InvalidValue - повтор палиндрома в рамках текущего пользователя
     */
    private int getPointNumber(User user, Phrase phrase) throws InvalidValue {

        if (scoringRepo.containsUserPhrase(user, phrase)) {
            throw new InvalidValue("Such palindrome is already registered in the database");
        }
        else {
            Pair<Integer, Integer> conversionRate = conversionRateRepo.getConversion(new Date());
            if (null == conversionRate) {
                conversionRate = conversionRateRepo.addConversionRate(1, 1);
            }

            int phraseLength = phrase.length();
            return pointCalc.calculate(conversionRate, phraseLength);
        }
    }

    /**
     * Получить фразу палиндрома из репозитория по строке
     * @param value - строковое значение фразы
     * @return - экземпляр сущность фраза
     * @throws InvalidValue - не корректное значение палиндрома
     */
    private Phrase getPhrase(String value) throws InvalidValue {
        value = value.trim();
        if (value.isEmpty()) {
            throw new InvalidValue("Palindrome must not be blank");
        }

        Phrase phrase = phraseRepo.getPhraseByValue(value);
        if (null == phrase) {
            phrase = phraseRepo.save(value);
        }
        return phrase;
    }

    /**
     * Добавить курс конвертации букв палиндромов в баллы
     * @param numLetter - число букв
     * @param numPoint - соответствующее число баллов
     */
    public void addConversionRate(int numLetter, int numPoint) {
        conversionRateRepo.addConversionRate(numLetter, numPoint);
    }
}
