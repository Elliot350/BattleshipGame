package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class SetupPlayerAction extends PlayerGameAction {

    public SetupPlayerAction(BattleshipPlayer player) {
        super(player);
        waitTime = 1000;
    }

    @Override
    public void perform() {
        player.startPlacingShips(this);
    }

    public void donePlacingShips() {
        startSubActions();
    }

    @Override
    public String toString() {
        return "Setup " + player;
    }
}
