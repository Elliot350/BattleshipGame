package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class RegisterHitAction extends PlayerGameAction {

    private final int col;
    private final int row;

    public RegisterHitAction(BattleshipPlayer playerHit, int col, int row) {
        super(playerHit);
        this.col = col;
        this.row = row;
    }

    @Override
    public void perform() {
        boolean hit = player.isHit(col, row);
        if (hit) {
            player.getOpponent().getEnemyBoard().placeHit(col, row);
            if (parent instanceof TurnAction turnAction) {
                turnAction.getNextTurn().addImmediateActions(
                        new WaitAction(1000),
                        new ReceiveShotAction(player, col, row),
                        new SimpleAction(() -> {
                            player.getPlayerBoard().placeHit(col, row);
                            player.receiveShot(col, row);
                        })
                );
            }
        } else {
            player.getOpponent().getEnemyBoard().placeMiss(col, row);
            if (parent instanceof TurnAction turnAction) {
                turnAction.getNextTurn().addImmediateActions(
                        new WaitAction(750),
                        new ReceiveShotAction(player, col, row),
                        new SimpleAction(() -> {
                            player.getPlayerBoard().placeMiss(col, row);
                        })
                );
            }
        }
        finishAction(100);
    }

    @Override
    public String toString() {
        return "Register hit at " + col + ", " + row + " for " + player;
    }
}
