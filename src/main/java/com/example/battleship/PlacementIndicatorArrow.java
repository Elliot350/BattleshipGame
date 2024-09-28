package com.example.battleship;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PlacementIndicatorArrow extends GridItem {

    private static final double ARROW_HEIGHT = 5;

    private final Pane arrowPane;

    public PlacementIndicatorArrow() {
        setPrefSize(BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH);
        arrowPane = createArrowPane();
        getChildren().add(arrowPane);
    }

    public void setDirection(Direction direction) {
        arrowPane.setRotate(direction.getRotationValue());
    }

    private Pane createArrowPane() {
        Pane pane = new Pane();
        pane.setPrefSize(BattleshipGame.CELL_WIDTH, BattleshipGame.CELL_WIDTH);
        Polygon polygon = createDirectionalArrow();
        pane.getChildren().add(polygon);
        polygon.relocate(0, BattleshipGame.CELL_WIDTH - 2 * ARROW_HEIGHT);
        return pane;
    }

    private Polygon createDirectionalArrow() {
        Polygon polygon = new Polygon(
                BattleshipGame.CELL_WIDTH / 2, 0,
                BattleshipGame.CELL_WIDTH, ARROW_HEIGHT,
                0, ARROW_HEIGHT
        );
        polygon.setFill(Color.rgb(50, 200, 50));
        return polygon;
    }
}
