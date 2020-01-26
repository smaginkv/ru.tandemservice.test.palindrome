package ru.tandemservice.palindrome.bd;

import ru.tandemservice.palindrome.entity.Phrase;

public interface PhraseRepo {
    Phrase getPhraseByValue(String value);

    Phrase save(String value);
}
