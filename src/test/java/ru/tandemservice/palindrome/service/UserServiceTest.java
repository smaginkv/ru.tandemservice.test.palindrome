package ru.tandemservice.palindrome.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.application.Context;
import ru.tandemservice.palindrome.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = Context.getContext().getUserService();
        user = new User("vasya");
    }

    @Test
    void shouldOkWhenRightUser() throws InvalidValue {
        @SuppressWarnings("SpellCheckingInspection")
        User vasya = userService.getByName("vasya");
        assertEquals(user, vasya);
    }

    @Test
    void shouldThrowWhenEmptyUser() {
        Exception exception = assertThrows(InvalidValue.class, () -> userService.getByName(" "));
        assertEquals("User name must not be blank", exception.getMessage());
    }
}