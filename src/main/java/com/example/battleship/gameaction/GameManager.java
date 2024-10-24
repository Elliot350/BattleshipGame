package com.example.battleship.gameaction;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private static final List<Label> actionNames = new ArrayList<>();
    private static final VBox actionList = new VBox();
    private static final List<GameAction> actions = new ArrayList<>();
    private static GameAction currentAction;
    private static final Timeline actionCaller = new Timeline(
            new KeyFrame(
                    Duration.millis(1),
                    event -> runAction()
            )
    );

    public static void startActions() {
        actionCaller.setCycleCount(Animation.INDEFINITE);
        actionCaller.play();
    }


    public static void addActions(GameAction... actionsToAdd) {
        for (GameAction action : actionsToAdd) {
            if (!actions.contains(action)) actions.add(action);
        }
    }

    public static void addToStart(GameAction... actions) {
        addActions(0, actions);
    }

    public static void addActions(int index, GameAction... actionsToAdd) {
        int tmp = index;
        for (GameAction action : actionsToAdd) {
            if (actions.contains(action)) continue;
            actions.add(tmp++, action);
        }
    }

    private static void runAction() {
        if (currentAction != null && !currentAction.done()) return;
        if (actions.isEmpty()) {
            System.out.println("No more actions");
            actionCaller.stop();
            return;
        }
        updateList();
        currentAction = actions.removeFirst();
        currentAction.doAction();
    }

    public static void clearActions() {
        actions.clear();
    }

    public static boolean contains(GameAction action) {
        return actions.contains(action);
    }

    public static List<GameAction> getActions() {
        return actions;
    }

    public static GameAction getNextAimAction() {
        for (GameAction action : actions) {
            if (action instanceof AimAction) {
                return action;
            }
        }
        return null;
    }

    public static void updateList() {
        while (actionNames.size() > actions.size()) {
            Label l = actionNames.removeFirst();
            actionList.getChildren().remove(l);
        }
        while (actionNames.size() < actions.size()) {
            Label l = new Label();
            actionNames.add(l);
            actionList.getChildren().add(l);
        }
        for (int i = 0; i < actions.size(); i++) {
            actionNames.get(i).setText(actions.get(i).getClass().getSimpleName());
        }
    }

    public static VBox getActionList() {
        return actionList;
    }

    public static void printActions() {
        System.out.println("Actions:");
        for (GameAction action : actions) {
            System.out.println(action.getClass().getSimpleName());
        }
    }
}
