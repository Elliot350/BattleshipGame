package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public abstract class PlayerGameAction extends GameAction {

    protected final BattleshipPlayer player;

    public PlayerGameAction(BattleshipPlayer player) {
        this.player = player;
    }
}
