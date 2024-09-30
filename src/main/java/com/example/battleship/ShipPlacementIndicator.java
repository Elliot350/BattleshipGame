package com.example.battleship;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacementIndicator {

    private final Grid<?> mask;
    private final int length;
    private final List<PlacementIndicatorSquare> squares;
    private final PlacementIndicatorArrow arrow;
    private final ShipPlacer.PlacementMode placementMode;
    private Direction direction;
    private Pane parent;

    public ShipPlacementIndicator(Pane parent, Grid<?> mask, int length, ShipPlacer.PlacementMode placementMode) {
        this.mask = mask;
        this.length = length;
        this.parent = parent;
        squares = new ArrayList<>();
        arrow = new PlacementIndicatorArrow();
        this.placementMode = placementMode;

        switch (placementMode) {
            case FRONT -> {

            }
            case BACK -> {
                for (int i = 0; i < length; i++) {
                    PlacementIndicatorSquare placementIndicatorSquare = new PlacementIndicatorSquare();
                    parent.getChildren().add(placementIndicatorSquare);
                    squares.add(placementIndicatorSquare);
                }
                setDirection(Direction.NORTH);
                parent.getChildren().add(arrow);
            }
            case MIDDLE -> {
                System.out.println("Middle not implemented!");
            }
        }
    }

    public void moveTo(int col, int row, Direction direction) {
        switch (placementMode) {
            case FRONT -> {
                System.out.println("Front not implemented!");
            }
            case BACK -> {
                for (int i = 0; i <= length; i++) {
                    int currentCol = col;
                    int currentRow = row;
                    switch (direction) {
                        case NORTH -> currentRow -= i;
                        case EAST -> currentCol += i;
                        case SOUTH -> currentRow += i;
                        case WEST -> currentCol -= i;
                    }
                    if (i == length) {
                        arrow.moveToGridPosition(currentCol, currentRow);
                        arrow.setDirection(direction);
                        break;
                    }
                    squares.get(i).moveToGridPosition(currentCol, currentRow);
                }
                updateSquares(col, row, direction);
            }
            case MIDDLE -> {
                System.out.println("Middle not implemented!");
            }
        }
    }

    public void setVisible(boolean visible) {
        for (PlacementIndicatorSquare square : squares) square.setVisible(visible);
        arrow.setVisible(visible);
    }

    public void remove() {
        parent.getChildren().removeAll(squares);
        parent.getChildren().remove(arrow);
    }

    public void setDirection(Direction direction) {
//        this.direction = direction;
//        switch (placementMode) {
//            case FRONT -> {
//                System.out.println("Front not implemented!");
//            }
//            case BACK -> {
//                for (int i = 0; i < length; i++) {
//                    double x = 0;
//                    double y = 0;
//                    switch (direction) {
//                        case NORTH -> y = -i * BattleshipGame.CELL_WIDTH;
//                        case EAST -> x = i * BattleshipGame.CELL_WIDTH;
//                        case SOUTH -> y = i * BattleshipGame.CELL_WIDTH;
//                        case WEST -> x = -i * BattleshipGame.CELL_WIDTH;
//                    }
////                    if (i == length) {
////                        directionArrow.setLayoutX(x);
////                        directionArrow.setLayoutY(y);
////                        break;
////                    }
//                    squares.get(i).setLayoutX(x);
//                    squares.get(i).setLayoutY(y);
//                }
//                updateSquares(col, row, direction.isHorizontal());
//            }
//            case MIDDLE -> {
//                System.out.println("Middle not implemented!");
//            }
//        }
    }

    private void updateSquares(int col, int row, Direction direction) {
        for (int i = 0; i < length; i++) {
            int currentCol = col;
            int currentRow = row;
            switch (direction) {
                case NORTH -> currentRow = row - i;
                case EAST -> currentCol = col + i;
                case SOUTH -> currentRow = row + i;
                case WEST -> currentCol = col - i;
            }
            squares.get(i).setValid(!(mask.outsideGrid(currentCol, currentRow) || mask.containsSomething(currentCol, currentRow)));
        }
    }
}
