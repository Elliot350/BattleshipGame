package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class SwitchToPlayerAction extends PlayerGameAction {

    private GameDisplay gameDisplay;

    public SwitchToPlayerAction(GameDisplay gameDisplay, BattleshipPlayer player) {
        super(player);
        this.gameDisplay = gameDisplay;
    }

    @Override
    public void perform() {
        addActions(
                new AnimationAction(player.getOpponent().getDoors().getCloseTimeline()),
                new WaitForConfirmationAction(gameDisplay, player),
                new AnimationAction(player.getDoors().getOpenTimeline())
        );
        startSubActions();
    }

    public BattleshipPlayer getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Switch from " + player.getOpponent() + " to " + player;
    }
}
