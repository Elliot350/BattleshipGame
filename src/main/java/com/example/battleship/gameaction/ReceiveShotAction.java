package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class ReceiveShotAction extends PlayerGameAction {

    private final int col;
    private final int row;

    public ReceiveShotAction(BattleshipPlayer player, int col, int row) {
        super(player);
        this.col = col;
        this.row = row;
    }

    @Override
    public void perform() {
        System.out.println("Would take a shot at " + col + ", " + row);
        finishAction(1000);
    }
}
