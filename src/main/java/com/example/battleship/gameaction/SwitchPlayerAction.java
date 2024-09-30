package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class SwitchPlayerAction extends PlayerGameAction {

    private GameDisplay gameDisplay;

    public SwitchPlayerAction(GameDisplay gameDisplay, BattleshipPlayer player) {
        super(player);
        this.gameDisplay = gameDisplay;
    }

    public void continueToPlayer() {
        finishAction();
    }

    @Override
    public void perform() {
        gameDisplay.takeAction(this);
    }

    public BattleshipPlayer getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Switch from " + player.getOpponent() + " to " + player;
    }
}
