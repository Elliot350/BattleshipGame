package com.example.battleship;

import com.example.battleship.gameaction.*;
import com.example.battleship.ship.ShipSegment;
import javafx.scene.layout.Pane;

public class BattleshipPlayer extends Pane {

    private final EnemyBoard enemyBoard;
    private final PlayerBoard playerBoard;
    private final ShipPlacer shipPlacer;
    private final BattleshipGame game;
    private SetupPlayerAction playerAction;
    private BattleshipPlayer opponent;
    private AimAction aimAction;
    private final GameDisplay gameDisplay;
    private String name;

    public BattleshipPlayer(BattleshipGame game, GameDisplay gameDisplay) {
        relocate(5, 5);
        this.game = game;
        this.gameDisplay = gameDisplay;

        enemyBoard = new EnemyBoard(this);
        playerBoard = new PlayerBoard();
        shipPlacer = new ShipPlacer(this);

        enemyBoard.relocate(0, 0);
        playerBoard.relocate(0, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 15);
        shipPlacer.relocate(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 5, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH + 15);

        getChildren().addAll(enemyBoard, playerBoard);
    }

    public void setName(String name) {
        this.name = name;
    }


    public void donePlacingShips() {
        getChildren().remove(shipPlacer);
        game.donePlacingShips(this);
        playerAction.donePlacingShips();
    }

    public void startPlacingShips(SetupPlayerAction source) {
        playerAction = source;
        getChildren().add(shipPlacer);
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
        return playerBoard.shipSegments.containsSomething(col, row);
    }

    public boolean receiveShot(int col, int row) {
        if (playerBoard.shipSegments.containsSomething(col, row)) {
            if (playerBoard.shipSegments.get(col, row) instanceof ShipSegment segment) {
                segment.getShot();
                System.out.println(segment + " at " + col + ", " + row + " was shot");
            }
            return true;
        } else {
            System.out.println(col + ", " + row + " was a miss");
            return false;
        }
    }

    // TODO: Yay or nay?
//    public GameAction takeTurnAction() {
//        BattleshipPlayer tmp = this;
//        return new SimpleAction(() -> {
//            ActionManager.addImmediateGameActions(
//                    new AimAction(tmp),
//                    new SwitchPlayerAction(gameDisplay, tmp.getOpponent()),
//                    tmp.getOpponent().takeTurnAction()
//            );
//        });
//    }

//    public GameAction registerHitAction(int col, int row) {
//        BattleshipPlayer tmp = this;
//        return new SimpleAction(() -> {
//            boolean hit = tmp.receiveShot(col, row);
//            if (hit) tmp.getOpponent().getEnemyBoard().placeHit(col, row);
//            else tmp.getOpponent().getEnemyBoard().placeMiss(col, row);
//        }, 100);
//    }

//    public GameAction takeAimAction() {
//        BattleshipPlayer tmp = this;
//        return new GameAction() {
//
//            public void takeShot(int col, int row) {
//                ActionManager.addImmediateGameActions(
//                        new MissileAnimation(tmp, col, row),
//                        new RegisterHit(tmp.getOpponent(), col, row)
//                );
//                finishAction(100);
//            }
//
//            @Override
//            public void perform() {
//                tmp.enemyBoard.startTargeting();
//            }
//        };
//    }


    @Override
    public String toString() {
        return name;
    }
}
