package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class WaitForConfirmationAction extends PlayerGameAction {

    private final GameDisplay display;

    public WaitForConfirmationAction(GameDisplay display, BattleshipPlayer player) {
        super(player);
        this.display = display;
    }

    public void continueToPlayer() {
        finishAction();
    }

    @Override
    protected void perform() {
        display.takeAction(this);
    }

    public BattleshipPlayer getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Switch from " + player.getOpponent() + " to " + player;
    }
}
