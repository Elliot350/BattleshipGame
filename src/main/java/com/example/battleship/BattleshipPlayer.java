package com.example.battleship;

import com.example.battleship.gameaction.GameManager;
import com.example.battleship.gameaction.PlayerWinAction;
import com.example.battleship.menu.PlayerDoors;
import com.example.battleship.ship.ShipSegment;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BattleshipPlayer extends StackPane {

    public enum PlayerState {
        IDLE,
        PLACING_SHIPS,
        AIMING
    }

    private final EnemyBoard enemyBoard;
    private final PlayerBoard playerBoard;
    private final ShipPlacer shipPlacer;
    private final BattleshipGame game;
    private BattleshipPlayer opponent;
    private final GameDisplay gameDisplay;
    private String name;
    private final PlayerDoors doors;
    private PlayerState state;
    private final VBox vBox;
    private int aimRow;
    private int aimCol;

    public BattleshipPlayer(BattleshipGame game, GameDisplay gameDisplay) {
//        setBorder(new Border(
//                new BorderStroke(
//                        Color.RED,
//                        BorderStrokeStyle.SOLID,
//                        CornerRadii.EMPTY,
//                        new BorderWidths(10)
//                )
//            )
//        );
        relocate(5, 5);
        this.game = game;
        this.gameDisplay = gameDisplay;
        state = PlayerState.IDLE;
        setAlignment(Pos.CENTER);

        enemyBoard = new EnemyBoard(this);
        playerBoard = new PlayerBoard();
        shipPlacer = new ShipPlacer(this);

        doors = new PlayerDoors();

//        enemyBoard.relocate(0, 0);
//        playerBoard.relocate(0, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 15);
//        shipPlacer.relocate(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 5, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 15);

        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);



//        vBox.getChildren().addAll(enemyBoard, playerBoard);
        Pane pane = new Pane();
//        vBox.setBorder(
//                new Border(
//                        new BorderStroke(
//                                Color.RED,
//                                BorderStrokeStyle.SOLID,
//                                CornerRadii.EMPTY,
//                                BorderWidths.DEFAULT
//                        )
//                )
//        );
//        pane.setBorder(
//                new Border(
//                        new BorderStroke(
//                                Color.LIGHTGREEN,
//                                BorderStrokeStyle.SOLID,
//                                CornerRadii.EMPTY,
//                                BorderWidths.DEFAULT
//                        )
//                )
//        );
//        pane.getChildren().add(playerBoard);
//        playerBoard.layoutXProperty().bind(
//                pane.widthProperty().divide(2).subtract(playerBoard.widthProperty().divide(2))
//        );
//        playerBoard.layoutYProperty().bind(
//                pane.heightProperty().divide(2).subtract(playerBoard.widthProperty().divide(2))
//        );
//        pane.getChildren().add(new Rectangle(150, 150, Color.GREEN));

//        StackPane tmp = new StackPane();
//        tmp.getChildren().add(playerBoard);

//        vBox.getChildren().add(tmp);
        vBox.getChildren().add(playerBoard);

        getChildren().add(vBox);
//        getChildren().add(doors);
    }

    public void setName(String name) {
        this.name = name;
    }


    public void donePlacingShips() {
        state = PlayerState.IDLE;
        vBox.getChildren().remove(shipPlacer);
    }

    public void startPlacingShips() {
        state = PlayerState.PLACING_SHIPS;
        vBox.getChildren().add(shipPlacer);
    }

    public void startAiming() {
        state = PlayerState.AIMING;
        enemyBoard.startTargeting();
    }

    public void shootAt(int col, int row) {
        state = PlayerState.IDLE;
        aimCol = col;
        aimRow = row;
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
            GameManager.clearActions();
            GameManager.addToStart(
                    new PlayerWinAction(gameDisplay, opponent)
            );
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

    public PlayerState getState() {
        return state;
    }

    public int getAimRow() {
        return aimRow;
    }

    public int getAimCol() {
        return aimCol;
    }

    public void resetAimVars() {
        aimCol = -1;
        aimRow = -1;
    }

    @Override
    public String toString() {
        return name;
    }
}
