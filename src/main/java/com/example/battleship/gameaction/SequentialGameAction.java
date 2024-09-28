package com.example.battleship.gameaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SequentialGameAction extends GameAction {

    protected final List<GameAction> actions;
    protected final List<GameAction> toAddActions;
    protected boolean started;
    protected GameAction currentAction;

    public SequentialGameAction(GameAction... actions) {
        this.actions = new ArrayList<>();
        this.toAddActions = new ArrayList<>();
        started = false;
        addActions(actions);
    }

    public void addActions(GameAction... actions) {
        for (GameAction action : actions) {
            action.setWhenDone(this::nextAction);
            this.actions.add(action);
        }
    }

    public void addActions(Collection<GameAction> actions) {
        for (GameAction action : actions) {
            action.setWhenDone(this::nextAction);
            this.actions.add(action);
        }
    }

    public void addActionsToStart(GameAction... actions) {
        for (GameAction action : actions) {
            action.setWhenDone(this::nextAction);
            this.toAddActions.add(action);
        }
    }

    public void addActionsToStart(Collection<GameAction> actions) {
        for (GameAction action : actions) {
            action.setWhenDone(this::nextAction);
            this.toAddActions.add(action);
        }
    }

    protected void nextAction() {
        currentAction = null;
        actions.addAll(0, toAddActions);
        toAddActions.clear();
        if (actions.isEmpty()) {
            finishAction();
            return;
        }
        currentAction = actions.removeFirst();
        currentAction.perform();
    }

    @Override
    public void perform() {
        if (started) {
            System.out.println("SequentialGameAction tried to start when is was already started!");
            return;
        }
        started = true;
        nextAction();
    }
}
