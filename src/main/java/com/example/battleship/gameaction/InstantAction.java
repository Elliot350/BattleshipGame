package com.example.battleship.gameaction;

public abstract class InstantAction extends GameAction {
    @Override
    public boolean done() {
        return true;
    }
}
