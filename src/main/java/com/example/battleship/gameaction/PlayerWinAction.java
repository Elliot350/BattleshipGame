package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

public class PlayerWinAction extends InstantAction {

    private GameDisplay display;
    private BattleshipPlayer winningPlayer;

    public PlayerWinAction(GameDisplay display, BattleshipPlayer winningPlayer) {
        this.display = display;
        this.winningPlayer = winningPlayer;
    }

    @Override
    public void doAction() {
        GameManager.addToStart(
                new AnimationAction(winningPlayer.getOpponent().getDoors().getCloseTimeline()),
                new SimpleGameAction(() -> display.showWinner(winningPlayer))
        );
    }
}
