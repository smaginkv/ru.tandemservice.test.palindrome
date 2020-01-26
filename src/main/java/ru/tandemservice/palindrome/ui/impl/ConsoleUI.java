package ru.tandemservice.palindrome.ui.impl;

import javafx.util.Pair;
import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.bh.Game;
import ru.tandemservice.palindrome.entity.User;
import ru.tandemservice.palindrome.ui.UI;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс который осуществляет взаимодействие с пользователем через консоль
 * @author Smagin K.V.
 */
public class ConsoleUI implements UI {

    private User user;
    private Game game;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Запуск бесконечного цикла ожидания команд
     */
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            printGreeting();
            try {
                int operationNumber = in.nextInt();
                in.nextLine();

                if (!chooseOperationNumber(operationNumber, in)) {
                    break;
                }
            } catch (InputMismatchException e) {
                in.next();
                clearConsole();
                System.err.println("wrong operation, try again.");
            }
        }
        in.close();
        System.err.println("Exit application");
        System.exit(0);
    }

    /**
     * Печать приветствия перед выполнением следующего шага
     */
    private void printGreeting() {

        System.out.printf("actual user is %s \n", user);
        if (null == user) {
            System.out.println("1. select actual user");
        }
        else {
            System.out.println("1. change actual user");
        }
        System.out.println("2. try to enter the next palindrome");
        System.out.println("3. leader board");
        System.out.println("4. add new conversion rate");
        System.out.println("0. exit");
        System.out.println("Enter operation number: ");
    }

    /**
     * Выбор операции, которую пользователь указал по номеру
     * @param operationNumber - номер операции
     * @param in - сканер для обработки дальнейших команд
     * @return - признак прекращения работы и выхода из приложения
     */
    private boolean chooseOperationNumber(int operationNumber, Scanner in) {
        if (operationNumber == 1) {
            selectUser(in);
        }
        else if (operationNumber == 2) {
            enterPalindrome(in);
        }
        else if (operationNumber == 3) {
            getLeaderBoard();
        }
        else if (operationNumber == 4) {
            addConversionRate(in);
        }
        else if (operationNumber == 0) {
            return false;
        }
        else {
            clearConsole();
            System.err.println("wrong operation, try again.");
        }
        return true;
    }

    /**
     * Операция выбора пользователя по имени
     * @param in - сканер для обработки дальнейших команд
     */
    private void selectUser(Scanner in) {
        System.out.print("Enter user name: ");
        String userName = in.nextLine();

        try {
            user = game.login(userName);
        } catch (InvalidValue e) {
            System.err.println(e.getMessage());
            return;
        }

        clearConsole();
        System.out.println("Done!");

    }

    /**
     * Операция по вводу палиндрома
     * @param in - сканер для обработки дальнейших команд
     */
    private void enterPalindrome(Scanner in) {
        System.out.print("Enter palindrome: ");
        String palindrome = in.nextLine();

        try {
            game.setPalindrome(user, palindrome);
        } catch (InvalidValue e) {
            clearConsole();
            System.err.println(e.getMessage());
            return;
        }

        clearConsole();
        System.out.println("Done!");
    }

    /**
     * Операция по выводу доски лидеров
     */
    private void getLeaderBoard() {
        Set<Pair<User, BigInteger>> leaders = game.getLeaders();
        clearConsole();
        System.out.println(leaders);
    }

    /**
     * Операция по добавлению курса конверсии букв палиндрома в баллы
     * @param in - сканер для обработки дальнейших команд
     */
    private void addConversionRate(Scanner in) {
        System.out.print("Enter conversion rate in format: [(int)numLetter (int)numPoint]: ");
        int numLetter = in.nextInt();
        int numPoint = in.nextInt();
        in.nextLine();

        game.addConversionRate(numLetter, numPoint);

        clearConsole();
        System.out.println("Done!");
    }

    /**
     * Операция по отделению следующего шага от предыдущих на консоли
     */
    private void clearConsole() {
        System.out.println();
        System.out.println();
    }
}
