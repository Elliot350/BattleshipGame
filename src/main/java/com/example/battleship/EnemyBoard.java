package com.example.battleship;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyBoard extends Board {

    private Grid<BoardSquare> background;
    private BattleshipPlayer player;
    private PlacementIndicatorSquare square;
    private boolean targeting;

    public EnemyBoard(BattleshipPlayer player) {
        this.player = player;
        background = new Grid<>(BattleshipGame.BOARD_WIDTH, BattleshipGame.BOARD_WIDTH, BoardSquare.class);
        Rectangle backgroundRect = new Rectangle(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH, backgroundColour);
        getChildren().add(backgroundRect);
        square = new PlacementIndicatorSquare();
        targeting = false;
        square.setVisible(false);

        for (int i = 0; i < BattleshipGame.BOARD_WIDTH; i++) {
            for (int j = 0; j < BattleshipGame.BOARD_WIDTH; j++) {
                BoardSquare square = new BoardSquare(i, j, Color.RED);
                background.set(i, j, square);
                getChildren().add(square);
            }
        }

        getChildren().add(square);

        setOnMouseMoved(event -> {
            if (targeting) {
                mouseMoved(event);
            }
        });
    }

    public void startTargeting() {
        square.setVisible(true);
        targeting = true;

        setOnMousePressed(event -> {
            int col = (int) (event.getSceneX() / BattleshipGame.CELL_WIDTH);
            int row = (int) (event.getSceneY() / BattleshipGame.CELL_WIDTH);

            setOnMousePressed(event1 -> {});
            square.setVisible(false);
            player.shootAt(col, row);
        });
    }

    public void mouseMoved(MouseEvent event) {
        int col = (int) (event.getSceneX() / BattleshipGame.CELL_WIDTH);
        int row = (int) (event.getSceneY() / BattleshipGame.CELL_WIDTH);

        square.moveToGridPosition(col, row);
    }

    @Override
    public void placeMiss(int col, int row) {
        getChildren().add(new MissIndicator(col, row));
    }

    @Override
    public void placeHit(int col, int row) {
        getChildren().add(new HitIndicator(col, row));
    }
}
