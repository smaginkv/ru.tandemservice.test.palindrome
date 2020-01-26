package ru.tandemservice.palindrome.bd.impl;

import javafx.util.Pair;
import ru.tandemservice.palindrome.bd.ScoringRepo;
import ru.tandemservice.palindrome.entity.Phrase;
import ru.tandemservice.palindrome.entity.User;

import java.math.BigInteger;
import java.util.*;

/**
 * Класс - имплементация репозитория, организованного в памяти
 * репозиторий хранит в себе все палиндромы которые были защитаны пользователю и баллы которые он получил
 * @author Smagin-KV
 */
public class ScoringRepoRamImpl implements ScoringRepo {
    private Map<User, Map<Phrase, Integer>> usersPhrases;

    public ScoringRepoRamImpl() {
        usersPhrases = new HashMap<>();
    }

    /**
     * Начислялись ли пользвателю баллы за текущую фразу?
     * @param user - пользователь для которого осуществляется проверка
     * @param phrase - палиндром для которого осуществляется проверка
     * @return - был ли повтор палиндрома
     */
    @Override
    public boolean containsUserPhrase(User user, Phrase phrase) {
        if (!usersPhrases.containsKey(user)) {
            return false;
        }
        Map<Phrase, Integer> phrases = usersPhrases.get(user);
        return phrases.containsKey(phrase);
    }

    /**
     * Сохранить палиндром пользователя в репозиторий
     * @param user - текщий пользователь
     * @param phrase - палиндром пользователя
     * @param pointNumber - число начисленных баллов
     * @return - число начисленных баллов за палиндром
     */
    @Override
    public int save(User user, Phrase phrase, int pointNumber) {
        Map<Phrase, Integer> phrasePoint = usersPhrases.computeIfAbsent(user, k -> new HashMap<>());
        phrasePoint.put(phrase, pointNumber);

        return pointNumber;
    }

    /**
     * Получить лидеров игры
     * @param topPlayers - количество топ игроков
     * @return - Список кортежей {игрок, баллы} в порядке убывания баллов
     */
    @Override
    public Set<Pair<User, BigInteger>> getTopLeaders(int topPlayers) {

        TreeSet<Pair<User, BigInteger>> points = new TreeSet<>(getComparator());
        for (Map.Entry<User, Map<Phrase, Integer>> userPhrase : usersPhrases.entrySet()) {
            int point = 0;
            for (Map.Entry<Phrase, Integer> phrases : userPhrase.getValue().entrySet()) {
                point+= phrases.getValue();
            }
            points.add(new Pair<>(userPhrase.getKey(), BigInteger.valueOf(point)));
        }

        if (points.size() <= topPlayers) {
            return points;
        }
        else {
            return points.headSet(getPairByNumber(points, topPlayers));
        }

    }

    /**
     * Комапаратор для сортировки кортежей в порядке убывания, очень уж не хотелось писать лишний класс с кортежем
     * @return - desk comparator
     */
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

    /**
     * Получение, например 3-го сверху игрока, чтобы по нему огранчить список игроков
     * sorry не придумал как это красивее оганизовать
     * @param points - список игроков
     * @param topPlayers - необходимое
     * @return - первый кортеж сверху, не входящий в топ
     */
    private Pair<User, BigInteger> getPairByNumber(TreeSet<Pair<User, BigInteger>> points, int topPlayers) {
        Iterator<Pair<User, BigInteger>> iterator = points.iterator();
        while (topPlayers-- > 0) {
            iterator.next();
        }
        return iterator.next();
    }
}
