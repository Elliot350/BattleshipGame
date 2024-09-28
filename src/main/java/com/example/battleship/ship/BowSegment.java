package com.example.battleship.ship;

import com.example.battleship.BattleshipGame;
import com.example.battleship.Direction;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BowSegment extends ShipSegment {

    public BowSegment(Direction direction) {
        Pane pane = createShape();
        getChildren().add(pane);
        pane.setRotate(direction.getRotationValue());
        pane.relocate(0, 0);
    }

    private Pane createShape() {
        Pane pane = new Pane();
        Polygon shape = new Polygon(
                BattleshipGame.CELL_WIDTH / 2, 0,
                BattleshipGame.CELL_WIDTH - BattleshipGame.SHIP_BORDER_GAP - 3, 5,
                BattleshipGame.CELL_WIDTH - BattleshipGame.SHIP_BORDER_GAP, 15,
                BattleshipGame.SHIP_BORDER_GAP, 15,
                BattleshipGame.SHIP_BORDER_GAP + 3, 5
        );
        shape.relocate(BattleshipGame.SHIP_BORDER_GAP, BattleshipGame.CELL_WIDTH - 15);
        shape.setFill(SHIP_COLOUR);
        pane.getChildren().add(shape);
        pane.setPrefSize(BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH);
        return pane;
    }
}
