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

    public int addUsersPhrase(User user, String value) throws InvalidValue {
        if (null == user) {
            throw new InvalidValue("User must not be equals null");
        }

        Phrase phrase = getPhrase(value);

        return getPointNumber(user, phrase);
    }

    public Set<Pair<User, BigInteger>> getLeaders(int i) {
        return scoringRepo.getTopLeaders(i);
    }

    private int getPointNumber(User user, Phrase phrase) throws InvalidValue {

        if (scoringRepo.containsUserPhrase(user, phrase)) {
            throw new InvalidValue("Such palindrome is already registered in the database");
        }
        else {
            Pair<Integer, Integer> course = conversionRateRepo.getConversion();
            int phraseLength = phrase.length();
            int phrasePoint = pointCalc.calculate(new Date(), course, phraseLength);

            return scoringRepo.save(user, phrase, phrasePoint);
        }
    }

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
}
