package ru.tandemservice.palindrome.bd.impl;

import ru.tandemservice.palindrome.bd.PhraseRepo;
import ru.tandemservice.palindrome.entity.Phrase;

import java.util.*;

/**
 * Класс - имплементация репозитория, организованного в памяти
 * репозиторий хранит в себе все палиндромы (фразы) которые использовались в игре
 * @author Smagin-KV
 */
public class PhraseRepoRamImpl implements PhraseRepo {
    private Map<String, Phrase> phrases;

    public PhraseRepoRamImpl() {
        this.phrases = new HashMap<>();
    }

    @Override
    public Phrase getPhraseByValue(String value) {
        return phrases.get(value);
    }

    @Override
    public Phrase save(String value) {
        Phrase phrase = new Phrase(value);
        phrases.put(value, phrase);
        return phrase;
    }
}
