package com.example.battleship.gameaction;

import java.util.ArrayList;
import java.util.List;

// TODO: Do I just make this a sequential game action??? Is that good practice or not?
public class ActionManager {

    private static final GameAction currentAction = new GameAction() {
        @Override
        protected void perform() {
            startSubActions();
        }
    };

    public static void addImmediateGameActions(GameAction... actions) {
        currentAction.addImmediateActions(actions);
    }

    public static void addGameActions(GameAction... actions) {
        currentAction.addActions(actions);
    }

    public static void printActions() {
        currentAction.printActions();
    }

    public static GameAction getCurrentAction() {
        return currentAction;
    }
}
