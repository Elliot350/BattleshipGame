package com.example.battleship;

import com.example.battleship.gameaction.*;
import javafx.animation.Timeline;

public class BattleshipGame {

    public static final double CELL_WIDTH = 25;
    public static final double SHIP_WIDTH = 15;
    public static final double SHIP_BORDER_GAP = (CELL_WIDTH - SHIP_WIDTH) / 2;
    public static final int BOARD_WIDTH = 10;

    private final GameDisplay gameDisplay;
    private final BattleshipPlayer player1;
    private final BattleshipPlayer player2;

    public BattleshipGame(GameDisplay gameDisplay) {
        this.gameDisplay = gameDisplay;

        player1 = new BattleshipPlayer(this, gameDisplay);
        player2 = new BattleshipPlayer(this, gameDisplay);
        player1.setOpponent(player2);
        player2.setOpponent(player1);

        ActionManager.addGameActions(
                new SetupPlayerAction(player1),
                new SwitchPlayerAction(gameDisplay, player2),
                new SetupPlayerAction(player2),
                new SwitchPlayerAction(gameDisplay, player1),
                new TurnAction(player1, gameDisplay)
        );

        ActionManager.takeNextGameAction();
    }

    public void startGame() {
        gameDisplay.setDisplay(player1);
    }

    public void donePlacingShips(BattleshipPlayer player) {
//        if (player == player1) {
//            gameDisplay.switchToPlayer(player2);
//        } else {
//            gameDisplay.switchToPlayer(player1);
//            player1.takeShot();
//        }
    }
}
