package com.example.battleship.gameaction;

public abstract class GameAction {

    public abstract void doAction();

    public abstract boolean done();

    public void andThen(GameAction... actions) {
        if (GameManager.contains(this)) {
            GameManager.addActions(GameManager.getActions().indexOf(this) + 1, actions);
        }
    }

    public void before(GameAction... actions) {
        if (GameManager.contains(this)) {
            GameManager.addActions(GameManager.getActions().indexOf(this), actions);
        }
    }
}
