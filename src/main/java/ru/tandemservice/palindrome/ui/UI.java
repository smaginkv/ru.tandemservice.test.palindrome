package ru.tandemservice.palindrome.ui;

import ru.tandemservice.palindrome.bh.Game;

/**
 * Интерфейс для подмены класса-имплементации программы
 */
public interface UI {
    void run();
    void setGame(Game game);
}
