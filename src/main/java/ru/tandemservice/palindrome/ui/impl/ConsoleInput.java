package ru.tandemservice.palindrome.ui.impl;

import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.bh.Game;
import ru.tandemservice.palindrome.entity.User;
import ru.tandemservice.palindrome.ui.UI;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

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
                System.out.println("wrong operation, try again.");
            }
        }
        in.close();// terminate Treads?
        System.exit(0);
    }

    private void printGreeting() {

        if (null == user) {
            System.out.println("1. select actual user");
        }
        else {
            System.out.println("1. change actual user");
        }

        System.out.println("2. try to enter the next palindrome");
        System.out.println("3. leader board");
        System.out.println("0. exit");
        System.out.print("Enter operation number: ");
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
            System.out.println("wrong operation, try again.");
        }
        return true;

    }

    private void selectUser(Scanner in) {
        System.out.print("Enter user name: ");
        String userName = in.nextLine();

        try {
            user = game.login(userName);
        } catch (InvalidValue e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return;
        }

        clearConsole();
        System.out.println("Done!");
    }

    private void getLeaderBoard() {
        Map<Integer, User> leaders = game.getLeaders();
        clearConsole();
        System.out.println(leaders);
    }

    private void clearConsole() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
