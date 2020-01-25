package ru.tandemservice.palindrome.bh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.application.Context;
import ru.tandemservice.palindrome.entity.User;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private User user;

    @BeforeEach
    void setUp() {
        game = Context.getContext().getGame();
        user = new User("vasya");
    }

    @Test
    void shouldThrowWhenEmptyUser() {
        Exception exception = assertThrows(InvalidValue.class, () -> game.login(" "));
        assertEquals("User name must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowsWhenEmptyUser() {
        Exception exception = assertThrows(InvalidValue.class, () -> game.setPalindrome(null, " "));
        assertEquals("User must not be equals null", exception.getMessage());
    }

    @Test
    void shouldThrowsWhenEmptyPalindrome() {
        Exception exception = assertThrows(InvalidValue.class, () -> game.setPalindrome(user, " "));
        assertEquals("Palindrome must not be blank", exception.getMessage());
    }

    @Disabled("Перенести в service")
    @Test
    void shouldThrowsWhenDoublePalindrome() throws InvalidValue {
        game.setPalindrome(user, "топот");
        Exception exception = assertThrows(InvalidValue.class, () -> game.setPalindrome(user, "топот"));
        assertEquals("Such palindrome is already registered in the database", exception.getMessage());
    }

    @Disabled("Перенести в verifier")
    @Test
    void shouldThrowsWhenWrongPalindrome() {
        Exception exception = assertThrows(InvalidValue.class, () -> game.setPalindrome(user, "бар"));
        assertEquals("Such phrase is not palindrome", exception.getMessage());
    }

    @Disabled("Перенести в service")
    @Test
    void shouldOkWhenRightUser() throws InvalidValue {
        @SuppressWarnings("SpellCheckingInspection")
        User vasya = game.login("vasya");
        assertEquals(user, vasya);
    }

    @Disabled("Перенести в service")
    @Test
    void shouldOkWhenRightPalindrome() throws InvalidValue {
        int pointNumber = game.setPalindrome(user, "топот");
        assertEquals(5, pointNumber);
    }

    @Disabled("Перенести в service")
    @Test
    void shouldOkWhenGetLeaders() throws InvalidValue {
        User user1 = game.login("vasya");
        game.setPalindrome(user1, "топот");
        User user2 = game.login("petya");
        game.setPalindrome(user2, "топот");
        game.login("misha");
        Map<Integer, User> leaders = game.getLeaders();
        assertTrue( leaders.size()==2 && leaders.containsValue(user1) && leaders.containsValue(user2));
    }

}