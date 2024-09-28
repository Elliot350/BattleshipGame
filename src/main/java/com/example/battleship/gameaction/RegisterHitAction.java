package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class RegisterHitAction extends PlayerGameAction {

    private final int col;
    private final int row;
    private final TurnAction turnAction;

    public RegisterHitAction(TurnAction turnAction, BattleshipPlayer player, int col, int row) {
        super(player);
        this.turnAction = turnAction;
        this.col = col;
        this.row = row;
    }

    @Override
    public void perform() {
        boolean hit = player.receiveShot(col, row);
        if (hit) {
            player.getEnemyBoard().placeHit(col, row);
            turnAction.getNextTurn().addInitialActions(
                    new ReceiveShotAction(player.getOpponent(), col, row)
            );
        } else {
            player.getEnemyBoard().placeMiss(col, row);
        }
        finishAction(100);
    }
}
