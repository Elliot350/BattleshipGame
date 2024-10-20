package com.example.battleship;

import com.example.battleship.gameaction.*;
import com.example.battleship.ship.ShipSegment;
import javafx.scene.layout.Pane;

public class BattleshipPlayer extends Pane {

    public enum PlayerState {
        IDLE,
        PLACING_SHIPS,
        AIMING
    }

    private final EnemyBoard enemyBoard;
    private final PlayerBoard playerBoard;
    private final ShipPlacer shipPlacer;
    private final BattleshipGame game;
//    private SetupPlayerAction playerAction;
    private BattleshipPlayer opponent;
    private AimAction aimAction;
    private final GameDisplay gameDisplay;
    private String name;
    private final PlayerDoors doors;
    private PlayerState state;

    public BattleshipPlayer(BattleshipGame game, GameDisplay gameDisplay) {
        relocate(5, 5);
        this.game = game;
        this.gameDisplay = gameDisplay;
        state = PlayerState.IDLE;

        enemyBoard = new EnemyBoard(this);
        playerBoard = new PlayerBoard();
        shipPlacer = new ShipPlacer(this);

        doors = new PlayerDoors();

        enemyBoard.relocate(0, 0);
        playerBoard.relocate(0, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 15);
        shipPlacer.relocate(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 5, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 15);

        getChildren().addAll(enemyBoard, playerBoard);
        getChildren().add(doors);
    }

    public void setName(String name) {
        this.name = name;
    }


    public void donePlacingShips() {
        getChildren().remove(shipPlacer);
        game.donePlacingShips(this);
        state = PlayerState.IDLE;

//        playerAction.donePlacingShips();
    }

    public void startPlacingShips() {
        getChildren().add(shipPlacer);
        state = PlayerState.PLACING_SHIPS;
    }

    public void startAiming(AimAction source) {
        aimAction = source;
        enemyBoard.startTargeting();
    }

    public void shootAt(int col, int row) {
//        if (game.shootAt(col, row, this)) {
//            enemyBoard.placeHit(col, row);
//        } else {
//            enemyBoard.placeMiss(col, row);
//        }
        aimAction.takeShot(col, row);
    }

    public void setOpponent(BattleshipPlayer opponent) {
        this.opponent = opponent;
    }

    public BattleshipPlayer getOpponent() {
        return opponent;
    }

    public EnemyBoard getEnemyBoard() {
        return enemyBoard;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public boolean isHit(int col, int row) {
        return playerBoard.getShipSegments().containsSomething(col, row);
    }

    public void receiveShot(int col, int row) {
        if (playerBoard.getShipSegments().containsSomething(col, row)) {
            ShipSegment segment = playerBoard.getShipSegments().get(col, row);
            segment.getShot();
            System.out.println(segment + " at " + col + ", " + row + " was shot");
        } else {
            System.out.println(col + ", " + row + " was a miss");
        }
        System.out.println(getSegmentsLeft() + " left");
        if (getSegmentsLeft() == 0) {
            System.out.println("All segments are shot!");
        }
    }

    public int getSegmentsLeft() {
        int count = 0;
        for (int i = 0; i < playerBoard.getShipSegments().getCols(); i++) {
            for (int j = 0; j < playerBoard.getShipSegments().getRows(); j++) {
                if (playerBoard.getShipSegments().containsSomething(i, j) && !playerBoard.getShipSegments().get(i, j).isShot()) {
                    count++;
                }
            }
        }
        return count;
    }

    public PlayerDoors getDoors() {
        return doors;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerState getState() {
        return state;
    }

    @Override
    public String toString() {
        return name;
    }
}
