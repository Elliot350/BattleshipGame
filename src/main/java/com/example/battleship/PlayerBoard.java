package com.example.battleship;

import com.example.battleship.ship.Ship;
import com.example.battleship.ship.ShipSegment;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoard extends Board {

    private Grid<BoardSquare> background;
    private final Grid<ShipSegment> shipSegments;
    private final List<CellClicked> actions;
    private final List<CellClicked> toRemove;

    public PlayerBoard() {
        actions = new ArrayList<>();
        toRemove = new ArrayList<>();

        Rectangle backgroundRect = new Rectangle(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH, backgroundColour);
        getChildren().add(backgroundRect);

        background = new Grid<>(BattleshipGame.BOARD_WIDTH, BattleshipGame.BOARD_WIDTH, BoardSquare.class);
        shipSegments = new Grid<>(BattleshipGame.BOARD_WIDTH, BattleshipGame.BOARD_WIDTH, ShipSegment.class);

        for (int i = 0; i < BattleshipGame.BOARD_WIDTH; i++) {
            for (int j = 0; j < BattleshipGame.BOARD_WIDTH; j++) {
                BoardSquare square = new BoardSquare(i, j, Color.BLACK);
                background.set(i, j, square);
                getChildren().add(square);
            }
        }

        // Temp stuff
        placeShip(0, 0, 3, Direction.EAST);
        placeShip(0, 3, 4, Direction.SOUTH);
        placeShip(2, 2, 2, Direction.WEST);

//        for (ShipSegment[] row : shipSegments) {
//            System.out.println(Arrays.toString(row));
//        }

        setOnMouseClicked(this::handleMouseClick);
    }

    public boolean canPlaceShip(int col, int row, int length, Direction direction) {
        for (int i = 0; i < length; i++) {
            int currentCol = col;
            int currentRow = row;
            switch (direction) {
                case NORTH -> currentRow -= i;
                case EAST -> currentCol += i;
                case SOUTH -> currentRow += i;
                case WEST -> currentCol -= i;
            }
            if (shipSegments.outsideGrid(currentCol, currentRow)) return false;
            if (shipSegments.containsSomething(currentCol, currentRow)) return false;
        }
        return true;
    }

    // Assumes the placement is valid
    public void placeShip(int col, int row, int length, Direction direction) {
        Ship ship = new Ship(length, direction);
        for (int i = 0; i < length; i++) {
            int currentCol = col;
            int currentRow = row;
            switch (direction) {
                case NORTH -> currentRow -= i;
                case EAST -> currentCol += i;
                case SOUTH -> currentRow += i;
                case WEST -> currentCol -= i;
            }
            shipSegments.set(currentCol, currentRow, ship.getSegments().get(i), true);
            getChildren().add(ship.getSegments().get(i));
        }
    }

    @Override
    public void placeMiss(int col, int row) {
        getChildren().add(new MissIndicator(col, row));
    }

    @Override
    public void placeHit(int col, int row) {
        getChildren().add(new HitIndicator(col, row));
    }

    public void handleMouseClick(MouseEvent event) {
        int xPos = (int) (event.getSceneX() / BattleshipGame.CELL_WIDTH);
        int yPos = (int) ((event.getSceneY() - getLayoutY()) / BattleshipGame.CELL_WIDTH);
        for (CellClicked action : actions) {
            action.accept(yPos, xPos);
        }
        actions.removeAll(toRemove);
        toRemove.clear();
    }

    public Grid<ShipSegment> getShipSegments() {
        return shipSegments;
    }

    public void addCellClicked(CellClicked cellClicked) {
        actions.add(cellClicked);
    }

    public void removeCellClicked(CellClicked cellClicked) {
        toRemove.add(cellClicked);
    }
}
