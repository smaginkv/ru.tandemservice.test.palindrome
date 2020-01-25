package ru.tandemservice.palindrome.bh;

import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.entity.User;
import ru.tandemservice.palindrome.service.ScoringService;
import ru.tandemservice.palindrome.service.UserService;

import java.util.Map;

public class Game {
    private UserService userService;
    private ScoringService scoringService;
    private PalindromeVerifier palindromeVerifier;

    public Game(UserService userService, ScoringService scoringService, PalindromeVerifier pv) {
        this.userService = userService;
        this.scoringService = scoringService;
        this.palindromeVerifier = pv;
    }

    public User login(String userName) throws InvalidValue {
        return userService.getByName(userName);
    }

    public int setPalindrome(User user, String value) throws InvalidValue {
        palindromeVerifier.verify(value);
        return scoringService.getPhraseByUser(user, value);
    }

    public Map<Integer, User> getLeaders() {
        return null;
    }
}
