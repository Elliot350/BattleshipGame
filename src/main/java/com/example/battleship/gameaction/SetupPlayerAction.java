package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class SetupPlayerAction extends InstantAction {

    private final BattleshipPlayer player;

    public SetupPlayerAction(BattleshipPlayer player) {
        this.player = player;
    }

    @Override
    public void doAction() {
        GameManager.addToStart(
                new SimpleGameAction(player::startPlacingShips),
                new WaitUntilAction(() -> player.getState() == BattleshipPlayer.PlayerState.IDLE)
        );
    }
}
