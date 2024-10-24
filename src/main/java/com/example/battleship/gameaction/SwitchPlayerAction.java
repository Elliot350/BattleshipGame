package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class SwitchPlayerAction extends InstantAction {

    private final GameDisplay display;
    private final BattleshipPlayer currentPlayer;

    public SwitchPlayerAction(GameDisplay display, BattleshipPlayer currentPlayer) {
        this.display = display;
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void doAction() {
        GameManager.addToStart(
                new AnimationAction(currentPlayer.getDoors().getCloseTimeline()),
                new SimpleGameAction(() -> display.switchPlayerTo(currentPlayer.getOpponent())),
                new WaitUntilAction(() -> display.getPlayer() == currentPlayer.getOpponent()),
                new AnimationAction(currentPlayer.getOpponent().getDoors().getOpenTimeline())
        );
    }
}
