package com.example.battleship.coroutine;

public class SimpleCoroutineStep extends CoroutineStep {

    private final Runnable runnable;

    public SimpleCoroutineStep(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
