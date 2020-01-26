package ru.tandemservice.palindrome.service;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.application.Context;
import ru.tandemservice.palindrome.bh.Game;
import ru.tandemservice.palindrome.entity.User;

import java.math.BigInteger;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ScoringServiceTest {

    private ScoringService scoringService;
    private User user;
    private Game game;

    @BeforeEach
    void setUp() {
        scoringService = Context.getContext().getScoringService();
        game = Context.getContext().getGame();
        user = new User("vasya");
    }

    @Test
    void shouldThrowsWhenEmptyUser() {
        Exception exception = assertThrows(InvalidValue.class, () -> scoringService.addUsersPhrase(null, " "));
        assertEquals("User must not be equals null", exception.getMessage());
    }

    @Test
    void shouldThrowsWhenEmptyPalindrome() {
        Exception exception = assertThrows(InvalidValue.class, () -> scoringService.addUsersPhrase(user, " "));
        assertEquals("Palindrome must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowsWhenDoublePalindrome() throws InvalidValue {
        scoringService.addUsersPhrase(user, "кок");
        Exception exception = assertThrows(InvalidValue.class, () -> scoringService.addUsersPhrase(user, "кок"));
        assertEquals("Such palindrome is already registered in the database", exception.getMessage());
    }

    @Test
    void shouldCorrectWhenChangedConversationRate() throws InvalidValue {
        scoringService.addConversionRate(1,1);
        User user1 = game.login("misha");
        int points = game.setPalindrome(user1, "лол");
        assertEquals(3, points);

        scoringService.addConversionRate(2,1);
        points = game.setPalindrome(user1, "кекек");
        assertEquals(2, points);

    }

    @Test
    void shouldOkWhenGetLeaders() throws InvalidValue {
        scoringService.addConversionRate(1,1);

        User user1 = game.login("vasya");
        game.setPalindrome(user1, "топот");
        User user2 = game.login("petya");
        game.setPalindrome(user2, "топот");
        game.login("misha");
        Set<Pair<User, BigInteger>> leaders = game.getLeaders();
        assertEquals(2, leaders.size());
    }

    @Test
    void shouldOkWhenGetMoreLeaders() throws InvalidValue {
        scoringService.addConversionRate(1,1);

        User user1 = game.login("vasya");
        game.setPalindrome(user1, "поп");
        User user2 = game.login("petya");
        game.setPalindrome(user2, "поп");
        User user3 = game.login("misha");
        game.setPalindrome(user3, "поп");
        User user4 = game.login("гриша");
        game.setPalindrome(user4, "поп");
        game.setPalindrome(user4, "вов");

        Set<Pair<User, BigInteger>> leaders = game.getLeaders();
        assertEquals(3, leaders.size());
    }
}