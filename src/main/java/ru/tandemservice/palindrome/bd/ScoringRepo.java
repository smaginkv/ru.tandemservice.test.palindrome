package ru.tandemservice.palindrome.bd;

import javafx.util.Pair;
import ru.tandemservice.palindrome.entity.Phrase;
import ru.tandemservice.palindrome.entity.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ScoringRepo {
    boolean containsUserPhrase(User user, Phrase phrase);

    int save(User user, Phrase phrase, int pointNumber);

    Set<Pair<User, BigInteger>> getTopLeaders(int i);
}
