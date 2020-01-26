package ru.tandemservice.palindrome.entity;

import java.util.Objects;

/**
 * Сущность фразы - палиндрома
 * @author Smagin-KV
 */
public class Phrase {
    private String value;

    public Phrase(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Phrase phrase = (Phrase) o;
        return Objects.equals(value, phrase.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int length() {
        if (null == value) {
            throw new NullPointerException();
        }
        return value.length();
    }
}
