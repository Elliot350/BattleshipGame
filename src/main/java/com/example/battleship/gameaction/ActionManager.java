package com.example.battleship.gameaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// TODO: Do I just make this a sequential game action??? Is that good practice or not?
public class ActionManager {

    private static GameAction currentAction;
    private static final List<GameAction> gameActions = new ArrayList<>();

    public static void addImmediateGameActions(GameAction... actions) {
        for (int i = 0; i < actions.length; i++) {
            gameActions.add(i, actions[i]);
        }
    }

    public static void addImmediateGameActions(Collection<GameAction> actions) {
        gameActions.addAll(0, actions);
    }

    public static void addGameActions(GameAction... actions) {
        gameActions.addAll(Arrays.asList(actions));
    }

    public static void addGameActions(Collection<GameAction> actions) {
        gameActions.addAll(actions);
    }

    public static void takeNextGameAction() {
        currentAction = null;
        printActions();
        if (gameActions.isEmpty()) {
            System.out.println("No more actions to take!");
            return;
        }
        currentAction = gameActions.removeFirst();
        currentAction.perform();
    }

    public static void printActions() {
        System.out.println("Game Actions:");
        for (GameAction gameAction : gameActions) {
            System.out.println(gameAction.getClass().getSimpleName());
        }
        if (currentAction != null) System.out.println("Current action: " + currentAction.getClass().getSimpleName());
    }

    public static void createNextTurn() {

    }
}
