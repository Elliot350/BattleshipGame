package com.example.battleship.coroutine;

import java.util.List;
import java.util.function.Supplier;

public class WaitUntilStep extends CoroutineStep {

    private final Supplier<Boolean> condition;

    public WaitUntilStep(Supplier<Boolean> condition) {
        this.condition = condition;
    }

    @Override
    public boolean isDone() {
        return condition.get();
    }

    @Override
    public void run() {}
}
