package com.example.battleship.ship;

import com.example.battleship.BattleshipGame;
import com.example.battleship.Direction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.zip.CheckedOutputStream;

public class MiddleSegment extends ShipSegment {
    private final Rectangle rectangle;

    public MiddleSegment(Direction direction) {
        if (direction.isHorizontal()) {
            rectangle = new Rectangle(BattleshipGame.CELL_WIDTH, BattleshipGame.SHIP_WIDTH);
            rectangle.relocate(0, (BattleshipGame.CELL_WIDTH - BattleshipGame.SHIP_WIDTH) / 2);
        } else {
            rectangle = new Rectangle(BattleshipGame.SHIP_WIDTH, BattleshipGame.CELL_WIDTH);
            rectangle.relocate((BattleshipGame.CELL_WIDTH - BattleshipGame.SHIP_WIDTH) / 2, 0);
        }
        rectangle.setFill(SHIP_COLOUR);
        rectangle.setStroke(SHIP_COLOUR);
        getChildren().add(rectangle);
    }

    @Override
    public void getShot() {
        rectangle.setFill(Color.DARKGRAY);
    }
}
