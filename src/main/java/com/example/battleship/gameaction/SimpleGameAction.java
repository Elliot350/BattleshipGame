package com.example.battleship.gameaction;

public class SimpleGameAction extends InstantAction {

    private final Runnable runnable;

    public SimpleGameAction(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void doAction() {
        runnable.run();
    }
}
