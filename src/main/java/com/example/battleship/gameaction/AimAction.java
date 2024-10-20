package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class AimAction extends InstantAction {

    private final GameDisplay display;
    private final BattleshipPlayer player;

    public AimAction(GameDisplay display, BattleshipPlayer player) {
        this.display = display;
        this.player = player;
    }

    @Override
    public void doAction() {
        player.startAiming();
        GameManager.addToStart(
                new WaitUntilAction(() -> player.getState() == BattleshipPlayer.PlayerState.IDLE),
                new ShootAction(display, player)
        );
    }
}
