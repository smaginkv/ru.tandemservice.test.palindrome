package ru.tandemservice.palindrome.bd;

import ru.tandemservice.palindrome.entity.Phrase;

/**
 * Интерфейс, используется для быстрой подмены репозитория (например если нужно хранить в bd)
 * @author Smagin-KV
 */
public interface PhraseRepo {
    Phrase getPhraseByValue(String value);

    Phrase save(String value);
}
