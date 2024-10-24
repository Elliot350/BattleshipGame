package com.example.battleship.gameaction;

import com.example.battleship.BattleshipGame;
import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class RegisterShotAction extends InstantAction {

    private final GameDisplay display;
    private final BattleshipPlayer player;
    private final int col;
    private final int row;

    // player is the one who shot
    public RegisterShotAction(GameDisplay display, BattleshipPlayer player, int col, int row) {
        this.display = display;
        this.player = player;
        this.col = col;
        this.row = row;
    }

    @Override
    public void doAction() {
        boolean hit = player.getOpponent().isHit(col, row);
        if (hit) {
            player.getEnemyBoard().placeHit(col, row);
        } else {
            player.getEnemyBoard().placeMiss(col, row);
        }
        GameManager.addToStart(
                new SwitchPlayerAction(display, player),
                new ReceiveShotAction(player.getOpponent(), col, row),
                new AimAction(display, player.getOpponent())
        );
    }
}
