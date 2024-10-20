package com.example.battleship.gameaction;

import java.util.function.BooleanSupplier;

public class WaitUntilAction extends GameAction {

    private final BooleanSupplier condition;

    public WaitUntilAction(BooleanSupplier condition) {
        this.condition = condition;
    }

    @Override
    public void doAction() {}

    @Override
    public boolean done() {
        return condition.getAsBoolean();
    }
}
