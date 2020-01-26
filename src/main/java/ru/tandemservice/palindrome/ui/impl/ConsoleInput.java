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

public class ConsoleInput implements UI {

    private User user;
    private Game game;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

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
        System.exit(0);
    }

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
        System.out.println("0. exit");
        System.out.println("Enter operation number: ");
    }

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
        else if (operationNumber == 0) {
            return false;
        }
        else {
            clearConsole();
            System.err.println("wrong operation, try again.");
        }
        return true;

    }

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

    private void enterPalindrome(Scanner in) {
        System.out.print("Enter palindrome: ");
        String palindrome = in.nextLine();

        try {
            game.setPalindrome(user, palindrome);
        } catch (InvalidValue e) {
            System.err.println(e.getMessage());
            return;
        }

        clearConsole();
        System.out.println("Done!");
    }

    private void getLeaderBoard() {
        Set<Pair<User, BigInteger>> leaders = game.getLeaders();
        clearConsole();
        System.out.println(leaders);
    }

    private void clearConsole() {
        System.out.println();
        System.out.println();
    }
}
