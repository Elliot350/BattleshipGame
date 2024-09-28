package com.example.battleship.gameaction;

public class SimpleAction extends GameAction {

    private final double waitTime;
    private final Runnable perform;

    public SimpleAction(Runnable runnable) {
        this(runnable, -1);
    }

    public SimpleAction(Runnable runnable, double waitTime) {
        this.perform = runnable;
        this.waitTime = waitTime;
    }

    @Override
    public void perform() {
        perform.run();
        if (waitTime > 0) finishAction(waitTime);
        else finishAction();
    }
}
