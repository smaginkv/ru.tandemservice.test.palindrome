package ru.tandemservice.palindrome.bh;

import javafx.util.Pair;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.entity.User;
import ru.tandemservice.palindrome.service.ScoringService;
import ru.tandemservice.palindrome.service.UserService;

import java.math.BigInteger;
import java.util.Set;

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
        return scoringService.addUsersPhrase(user, value);
    }

    public Set<Pair<User, BigInteger>>  getLeaders() {
        return scoringService.getLeaders(3);
    }

    public void addConversionRate(int numLetter, int numPoint) {
        scoringService.addConversionRate(numLetter, numPoint);
    }
}
