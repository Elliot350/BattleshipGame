package com.example.battleship.ship;

import com.example.battleship.BattleshipGame;
import com.example.battleship.Direction;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class SternSegment extends ShipSegment {

    public SternSegment(Direction direction) {
        Pane pane = createShape();
        getChildren().add(pane);
        pane.setRotate(direction.getRotationValue());
    }

    private Pane createShape() {
        Pane pane = new Pane();
        pane.setPrefSize(BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH);
        Polygon shape = new Polygon(
//                BattleshipGame.SHIP_BORDER_GAP, 0,
                BattleshipGame.CELL_WIDTH - BattleshipGame.SHIP_BORDER_GAP, 0,
                BattleshipGame.CELL_WIDTH - BattleshipGame.SHIP_BORDER_GAP, 10,
                15, 13,
                10, 13,
                BattleshipGame.SHIP_BORDER_GAP, 10,
                BattleshipGame.SHIP_BORDER_GAP, 0
        );
        shape.relocate(BattleshipGame.SHIP_BORDER_GAP, 0);
        shape.setFill(SHIP_COLOUR);
        pane.getChildren().add(shape);
        return pane;
    }
}
