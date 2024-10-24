package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class ShootAction extends InstantAction {

    private final GameDisplay display;
    private final BattleshipPlayer player;

    public ShootAction(GameDisplay display, BattleshipPlayer player) {
        this.display = display;
        this.player = player;
    }

    @Override
    public void doAction() {
        if (player.getAimCol() == -1 || player.getAimRow() == -1) {
            System.out.println("Player has not aimed!");
            return;
        }

        int col = player.getAimCol();
        int row = player.getAimRow();

        GameManager.addToStart(
                new MissileAnimationAction(player, col, row),
                new RegisterShotAction(display, player, col, row)
        );
    }
}
