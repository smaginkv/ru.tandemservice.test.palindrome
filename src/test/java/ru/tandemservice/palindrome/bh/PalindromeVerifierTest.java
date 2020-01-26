package ru.tandemservice.palindrome.bh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.application.Context;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeVerifierTest {
    private PalindromeVerifier verifier;
    @BeforeEach
    void setUp() {
        verifier = Context.getContext().getPalindromeVerifier();
    }

    @Test
    void shouldThrowWhenWrongPalindrome() {
        String value = "топ";
        Exception exception = assertThrows(InvalidValue.class, () -> verifier.verify(value));
        assertEquals("Such phrase is not palindrome", exception.getMessage());
    }

    @Test
    void shouldOkWhenExtraSymbols() throws InvalidValue {
        String value = "т оп!---|от";
        verifier.verify(value);
    }

    @Test
    void shouldOkWhenLongPhrase() throws InvalidValue {
        String value = "Madam, I’m Adam";
        verifier.verify(value);

        value ="Кони, топот, инок";
        verifier.verify(value);

        value ="Но не речь, а черен он";
        verifier.verify(value);

        value ="Идем, молод, долом меди";
        verifier.verify(value);

        value ="Чин зван мечем навзничь";
        verifier.verify(value);

        value ="Голод, чем меч долог";
        verifier.verify(value);

        value ="Пал, а норов худ и дух ворона лап";
        verifier.verify(value);

        value ="Яд, яд, дядя";
        verifier.verify(value);

        value ="Иди, иди!";
        verifier.verify(value);

        value ="Мороз в узел, лезу взором.";
        verifier.verify(value);

        value ="Солов зов, воз волос.";
        verifier.verify(value);

        value ="Колесо. Жалко поклаж. Оселок.";
        verifier.verify(value);

        value ="Сани, плот и воз, зов и толп и нас.";
        verifier.verify(value);

        value ="Горд дох, ход дрог.";
        verifier.verify(value);

        value ="И лежу. — Ужели?";
        verifier.verify(value);

        value ="Зол, гол лог лоз.";
        verifier.verify(value);

        value ="И к вам и трем с смерти мавки.";
        verifier.verify(value);
    }

}