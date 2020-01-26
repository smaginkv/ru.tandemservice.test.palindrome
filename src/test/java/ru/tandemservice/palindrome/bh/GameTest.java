package ru.tandemservice.palindrome.bh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.application.Context;
import ru.tandemservice.palindrome.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {
    private Game game;
    private User user;

    @BeforeEach
    void setUp() {
        game = Context.getContext().getGame();
        user = new User("vasya");
    }

    @Disabled("Перенести в verifier")
    @Test
    void shouldThrowsWhenWrongPalindrome() {
        Exception exception = assertThrows(InvalidValue.class, () -> game.setPalindrome(user, "бар"));
        assertEquals("Such phrase is not palindrome", exception.getMessage());
    }

    @Disabled("Перенести в caclPoin")
    @Test
    void shouldOkWhenRightPalindrome() throws InvalidValue {
        int pointNumber = game.setPalindrome(user, "топот");
        assertEquals(5, pointNumber);
    }
}