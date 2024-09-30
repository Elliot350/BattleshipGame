package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

import java.util.ArrayList;
import java.util.List;

public class TurnAction extends GameAction {

    private final BattleshipPlayer player;
    private final GameDisplay gameDisplay;
    private TurnAction nextTurn;

    public TurnAction(BattleshipPlayer player, GameDisplay gameDisplay) {
        super();
        this.player = player;
        this.gameDisplay = gameDisplay;
    }

    @Override
    public void perform() {
        nextTurn = new TurnAction(player.getOpponent(), gameDisplay);
        addActions(
                new AimAction(player, this),
                new WaitAction(1000),
                new SwitchPlayerAction(gameDisplay, player.getOpponent())
        );
        parent.addImmediateActions(nextTurn);
        startSubActions();
    }

    public TurnAction getNextTurn() {
        return nextTurn;
    }

    @Override
    public String toString() {
        return player + " turn";
    }
}
