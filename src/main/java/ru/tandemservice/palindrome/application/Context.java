package ru.tandemservice.palindrome.application;

import ru.tandemservice.palindrome.bh.Game;
import ru.tandemservice.palindrome.ui.UI;
import ru.tandemservice.palindrome.ui.impl.ConsoleInput;

import java.util.HashMap;

public class Context {
    private HashMap<String, Object> objectMap;

    public Context() {
        this.objectMap = new HashMap<>();
    }

    public static void main(String[] args) {
        Context context = new Context();
        UI ui = context.getUi();
        ui.run();
    }

    private UI getUi() {
        UI ui = (UI) objectMap.computeIfAbsent("ui", (k) -> new ConsoleInput());
        ui.setGame(getGame());
        return ui;
    }

    public Game getGame() {
        return (Game) objectMap.computeIfAbsent("game", (k)->new Game());
    }
}
