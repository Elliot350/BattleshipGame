package com.example.battleship.gameaction;

public class WaitAction extends GameAction {

    private final double waitTime;

    public WaitAction(double waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void perform() {
        finishAction(waitTime);
    }
}
