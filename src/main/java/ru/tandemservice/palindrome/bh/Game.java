package ru.tandemservice.palindrome.bh;

import javafx.util.Pair;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.entity.User;
import ru.tandemservice.palindrome.service.ScoringService;
import ru.tandemservice.palindrome.service.UserService;

import java.math.BigInteger;
import java.util.Set;

/**
 * Класс - который совмещает в себе все основыне сущности
 * @author Smagin-KV
 */
public class Game {
    private UserService userService;
    private ScoringService scoringService;
    private PalindromeVerifier palindromeVerifier;

    public Game(UserService userService, ScoringService scoringService, PalindromeVerifier pv) {
        this.userService = userService;
        this.scoringService = scoringService;
        this.palindromeVerifier = pv;
    }

    /**
     * Залогиниться
     * @param userName - имя пользователя
     * @return - экземпляр залогинившегося User
     * @throws InvalidValue - не корректно заполненное имя
     */
    public User login(String userName) throws InvalidValue {
        return userService.getByName(userName);
    }

    /**
     * Сохранить палиндром, получить за него баллы
     * @param user - текущий пользователь
     * @param value - фраза, которая возможно палиндром
     * @return - баллы за палиндром
     * @throws InvalidValue - повторное значение или не корректный палиндром
     */
    public int setPalindrome(User user, String value) throws InvalidValue {
        palindromeVerifier.verify(value);
        return scoringService.addUsersPhrase(user, value);
    }

    /**
     * Получить список лидеров
     * @return - список лидеров
     */
    public Set<Pair<User, BigInteger>>  getLeaders() {
        return scoringService.getLeaders(3);
    }

    /**
     * Установить новый курс конвертации букв в баллы
     * @param numLetter - количество букв
     * @param numPoint - количество баллов за буквы
     */
    public void addConversionRate(int numLetter, int numPoint) {
        scoringService.addConversionRate(numLetter, numPoint);
    }
}
