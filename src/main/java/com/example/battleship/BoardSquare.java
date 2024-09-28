package com.example.battleship;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardSquare extends GridItem {

    public BoardSquare(int col, int row, Color colour) {
        super(col, row);
        Rectangle rectangle = new Rectangle(BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(colour);
        getChildren().add(rectangle);
    }
}
