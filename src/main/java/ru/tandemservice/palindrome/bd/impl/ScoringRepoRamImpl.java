package ru.tandemservice.palindrome.bd.impl;

import javafx.util.Pair;
import ru.tandemservice.palindrome.bd.ScoringRepo;
import ru.tandemservice.palindrome.entity.Phrase;
import ru.tandemservice.palindrome.entity.User;

import java.math.BigInteger;
import java.util.*;


public class ScoringRepoRamImpl implements ScoringRepo {
    private Map<User, Map<Phrase, Integer>> usersPhrases;

    public ScoringRepoRamImpl() {
        usersPhrases = new HashMap<>();
    }

    @Override
    public boolean containsUserPhrase(User user, Phrase phrase) {
        if (!usersPhrases.containsKey(user)) {
            return false;
        }
        Map<Phrase, Integer> phrases = usersPhrases.get(user);
        return phrases.containsKey(phrase);
    }

    @Override
    public int save(User user, Phrase phrase, int pointNumber) {
        Map<Phrase, Integer> phrasePoint = usersPhrases.computeIfAbsent(user, k -> new HashMap<>());
        phrasePoint.put(phrase, pointNumber);

        return pointNumber;
    }

    @Override
    public Set<Pair<User, BigInteger>> getTopLeaders(int i) {

        TreeSet<Pair<User, BigInteger>> points = new TreeSet<>(getComparator());
        for (Map.Entry<User, Map<Phrase, Integer>> userPhrase : usersPhrases.entrySet()) {
            int point = 0;
            for (Map.Entry<Phrase, Integer> phrases : userPhrase.getValue().entrySet()) {
                point+= phrases.getValue();
            }
            points.add(new Pair<>(userPhrase.getKey(), BigInteger.valueOf(point)));
        }
        if (points.size() <= i) {
            return points;
        }
        else {
            return points.headSet(getPairByNumber(points, i));
        }

    }
    private Comparator<Pair<User, BigInteger>> getComparator() {
        return (o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                Long l1 = o1.getKey().getId(), l2 = o2.getKey().getId();
                return l1.compareTo(l2);
            }
            else {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
    }

    private Pair<User, BigInteger> getPairByNumber(TreeSet<Pair<User, BigInteger>> points, int i) {
        Iterator<Pair<User, BigInteger>> iterator = points.iterator();
        while (i-- > 0) {
            iterator.next();
        }
        return iterator.next();
    }
}
