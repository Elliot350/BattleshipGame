package com.example.battleship;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class ShotIndicator extends GridItem {

    private static final double INDICATOR_THICKNESS = 5;

    protected Pane createIndicator(Color colour) {
        double cornerPos = 5 / Math.sqrt(2);
        Polygon arm1 = new Polygon(
            cornerPos, 0,
                BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH - cornerPos,
                BattleshipGame.CELL_WIDTH - cornerPos, BattleshipGame.CELL_WIDTH,
                0, cornerPos
        );
        Polygon arm2 = new Polygon(
                BattleshipGame.CELL_WIDTH - cornerPos, 0,
                BattleshipGame.CELL_WIDTH, cornerPos,
                cornerPos, BattleshipGame.CELL_WIDTH,
                0, BattleshipGame.CELL_WIDTH - cornerPos
        );
        Shape shape = Shape.union(arm1, arm2);
        shape.setFill(colour);
        return new Pane(shape);
    }
}
