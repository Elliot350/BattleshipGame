package com.example.battleship.gameaction;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class representing a game action. This game action can hold children actions, which it will start one after
 * the other after it itself has finished (called {@code nextAction()}).
 */
public abstract class GameAction {

    /**
     * The states the game action can be in.
     */
    private enum ActionState {
        IDLE,
        PERFORMING,
        WAITING_FOR_CHILDREN,
        FINISHED
    }

    private ActionState state;
    protected double waitTime;
    protected GameAction parent;
    protected GameAction currentAction;
    protected final List<GameAction> subActions;
    protected final List<GameAction> toAddActions;

    /**
     * Creates a new GameAction with a default wait time of 0ms.
     */
    public GameAction() {
        state = ActionState.IDLE;
        waitTime = 0;
        subActions = new ArrayList<>();
        toAddActions = new ArrayList<>();
    }

    /**
     * Starts the current action, first checks if we've been started before, and if not call {@link #perform()}.
     */
    public void startAction() {
        if (state != ActionState.IDLE) {
            System.out.println("GameAction tried to start when already started");
            return;
        }
        state = ActionState.PERFORMING;
        perform();
    }

    /**
     * An abstract method that will be called when the action starts. The action will do the things here, then wait until
     * {@link #startSubActions()} is called to indicate this action is done, and we can proceed with the children.
     */
    protected abstract void perform();

    /**
     * Add actions to the end of the sub-action list.
     * @param actions The actions to add
     */
    protected void addActions(GameAction... actions) {
        for (GameAction action : actions) {
            action.setParent(this);
            this.subActions.add(action);
        }
    }

    /**
     * Add actions to be done once the current sub-action is finished.
     * @param actions The actions to add
     */
    protected void addImmediateActions(GameAction... actions) {
        for (GameAction action : actions) {
            action.setParent(this);
            this.toAddActions.add(action);
        }
    }

    /**
     * Called when this action has finished all it needs to do. Will switch the state, and start the process of performing the
     * sub actions.
     */
    protected void startSubActions() {
        state = ActionState.WAITING_FOR_CHILDREN;
        nextAction();
    }

    /**
     * Called when a sub action has finished
     * @param subAction The action that just finished
     */
    private void subActionFinished(GameAction subAction) {
        nextAction();
    }

    /**
     * First adds the immediate game actions to the action list. Starts the next child action we have. If there are none left,
     * finish this action.
     */
    private void nextAction() {
        currentAction = null;
        subActions.addAll(0, toAddActions);
        toAddActions.clear();
        if (subActions.isEmpty()) {
            if (waitTime > 0) finishAction(waitTime);
            else finishAction();
            return;
        }
        currentAction = subActions.removeFirst();
        currentAction.startAction();
    }

    /**
     * Called to force this action to finish after a specified amount of time. Also called normally if {@code waitTime > 0},
     * when all the children have finished.
     *
     * @param delayTime The amount of time to wait until we fully finish, in ms
     */
    protected void finishAction(double delayTime) {
        state = ActionState.FINISHED;
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(delayTime),
                        event -> finishAction()
                )
        );
        timeline.play();
    }

    /**
     * Called to force this action to finish, also called normally when all the children have finished.
     */
    protected void finishAction() {
        state = ActionState.FINISHED;
        if (parent == null) {
            System.out.println(getClass().getSimpleName() + " finished with no parent!");
            return;
        }
        parent.subActionFinished(this);
    }

    /**
     * Sets the parent of this action.
     * @param parent The new parent
     */
    private void setParent(GameAction parent) {
        this.parent = parent;
    }

    /**
     * Prints an indented list of the action from this one.
     */
    public void printActions() {
        printActions(0);
    }

    /**
     * Prints an indented list of the actions left to do from this one. Will recursively call for each sub-action
     * while incrementing depth
     * @param depth The number of tabs to put at the start
     */
    private void printActions(int depth) {
        System.out.println("\t".repeat(depth) + this);
        if (currentAction != null) {
            System.out.println("\t".repeat(depth) + "Currently:");
            currentAction.printActions(depth + 1);
            if (!subActions.isEmpty()) System.out.println("\t".repeat(depth) + "And then:");
        }
        for (GameAction subAction : subActions) {
            subAction.printActions(depth + 1);
        }
    }

    @Override
    public String toString() {
        return "Blank GameAction";
    }
}
