package com.example.battleship;

import com.example.battleship.gameaction.*;
import javafx.stage.Stage;

public class BattleshipGame {

    public static final double CELL_WIDTH = 25;
    public static final double SHIP_WIDTH = 15;
    public static final double SHIP_BORDER_GAP = (CELL_WIDTH - SHIP_WIDTH) / 2;
    public static final int BOARD_WIDTH = 10;

    private final GameDisplay gameDisplay;
    private final BattleshipPlayer player1;
    private final BattleshipPlayer player2;

    public BattleshipGame(GameDisplay gameDisplay) {
        this(gameDisplay, "Player 1", "Player 2");
    }


    public BattleshipGame(GameDisplay gameDisplay, String player1Name, String player2Name) {
        this.gameDisplay = gameDisplay;

        player1 = new BattleshipPlayer(this, gameDisplay);
        player2 = new BattleshipPlayer(this, gameDisplay);
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        player1.setName(player1Name);
        player2.setName(player2Name);

        GameManager.addActions(
                new SetupPlayerAction(player1),
                new SimpleGameAction(() -> System.out.println("Done placing")),
                new SwitchPlayerAction(gameDisplay, player1),
                new SetupPlayerAction(player2),
                new SwitchPlayerAction(gameDisplay, player2),
                new AimAction(gameDisplay, player1)
        );
        GameManager.startActions();
    }

    public void startGame() {
        gameDisplay.setDisplay(player1);
    }
}
