package com.example.battleship.gameaction;

public class SimpleAction extends GameAction {

    private final Runnable perform;

    public SimpleAction(Runnable runnable) {
        this(runnable, 0);
    }

    public SimpleAction(Runnable runnable, double waitTime) {
        this.perform = runnable;
        this.waitTime = waitTime;
    }

    @Override
    protected void perform() {
        perform.run();
        if (waitTime > 0) finishAction(waitTime);
        else finishAction();
    }

    @Override
    public String toString() {
        return "Simple action";
    }
}
