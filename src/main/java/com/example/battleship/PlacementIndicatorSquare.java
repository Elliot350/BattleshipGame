package com.example.battleship;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class PlacementIndicatorSquare extends GridItem {

    private static final Color INVALID_COLOUR = Color.rgb(200, 50, 50);
    private static final Color VALID_COLOUR = Color.rgb(50, 200, 50);
    private static final double OUTER_RING_THICKNESS = 5;
    private static final double CROSS_ARM_THICKNESS = 5 / Math.sqrt(2);

    private final List<Shape> shapes;

    public PlacementIndicatorSquare() {
        shapes = new ArrayList<>();

        Rectangle outerRectangle = new Rectangle(BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH);
        Rectangle innerRectangle = new Rectangle(BattleshipGame.CELL_WIDTH - OUTER_RING_THICKNESS * 2, BattleshipGame.CELL_WIDTH - OUTER_RING_THICKNESS * 2);
        innerRectangle.setY(OUTER_RING_THICKNESS);
        innerRectangle.setX(OUTER_RING_THICKNESS);

        Shape outerRing = Shape.subtract(outerRectangle, innerRectangle);

        Polygon crossArm1 = new Polygon(
                0, 0,
                CROSS_ARM_THICKNESS, 0,
                BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH - CROSS_ARM_THICKNESS,
                BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH,
                BattleshipGame.CELL_WIDTH - CROSS_ARM_THICKNESS, BattleshipGame.CELL_WIDTH,
                0, CROSS_ARM_THICKNESS
        );

        Polygon crossArm2 = new Polygon(
                BattleshipGame.CELL_WIDTH, 0,
                BattleshipGame.CELL_WIDTH, CROSS_ARM_THICKNESS,
                CROSS_ARM_THICKNESS, BattleshipGame.CELL_WIDTH,
                0, BattleshipGame.CELL_WIDTH,
                0, BattleshipGame.CELL_WIDTH - CROSS_ARM_THICKNESS,
                BattleshipGame.CELL_WIDTH - CROSS_ARM_THICKNESS, 0
        );

        getChildren().addAll(outerRing, crossArm1, crossArm2);
        shapes.addAll(List.of(outerRing, crossArm1, crossArm2));
        setValid(true);
    }

    public void setValid(boolean valid) {
        for (Shape shape : shapes) shape.setFill(valid ? VALID_COLOUR : INVALID_COLOUR);
    }
}
