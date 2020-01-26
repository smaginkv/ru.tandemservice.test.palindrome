package ru.tandemservice.palindrome.bh;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

/**
 * Класс, который проверяет является ли текущая фраза палиндромом
 * @author Smagin-KV
 */
public class PalindromeVerifier {
    public void verify(String value) throws InvalidValue {
        String clearValue = value.replaceAll("[^A-zА-ЩЫЭЮЯа-щыэюя]", "");
        StringBuilder reverseString = new StringBuilder(clearValue).reverse();

        if (!reverseString.toString().equalsIgnoreCase(clearValue)) {
            throw new InvalidValue("Such phrase is not palindrome");
        }
    }
}
