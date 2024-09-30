package com.example.battleship.gameaction;

public class WaitAction extends GameAction {
    public WaitAction(double waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void addActions(GameAction... actions) {
        System.out.println("Actions were attempted to be added to a wait!");
    }

    @Override
    public void addImmediateActions(GameAction... actions) {
        System.out.println("Actions were attempted to be added to a wait!");
    }

    @Override
    public void perform() {
        finishAction(waitTime);
    }

    @Override
    public String toString() {
        return "Wait for " + waitTime + "ms";
    }
}
