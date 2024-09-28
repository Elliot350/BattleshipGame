package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class SetupPlayerAction extends PlayerGameAction {

    public SetupPlayerAction(BattleshipPlayer player) {
        super(player);
    }

    @Override
    public void perform() {
        player.startPlacingShips(this);
    }

    public void donePlacingShips() {
        finishAction(1000);
    }
}
