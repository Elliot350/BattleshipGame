package com.example.battleship.ship;

import com.example.battleship.BattleshipGame;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ShipSprite extends Pane {

    private static final Color SHIP_COLOUR = Color.NAVY;

    private final int length;
    private final Circle front;
    private final Rectangle middle;
    private final Circle back;

    // TODO: Replace all of these with ship segments?
    public ShipSprite(int length) {
        this.length = length;

        front = new Circle(BattleshipGame.CELL_WIDTH / 2);
        front.setFill(SHIP_COLOUR);
        front.setStroke(SHIP_COLOUR);

        middle = new Rectangle(BattleshipGame.CELL_WIDTH * (length - 1), BattleshipGame.CELL_WIDTH);
        middle.setFill(SHIP_COLOUR);
        middle.setStroke(SHIP_COLOUR);

        back = new Circle(BattleshipGame.CELL_WIDTH / 2);
        back.setFill(SHIP_COLOUR);
        back.setFill(SHIP_COLOUR);

        setHorizontal(true);

        getChildren().addAll(front, middle, back);
    }

    public void setHorizontal(boolean horizontal) {
        if (horizontal) {
            front.relocate(0, 0);
            middle.relocate(BattleshipGame.CELL_WIDTH / 2, 0);
            middle.setHeight(BattleshipGame.CELL_WIDTH);
            middle.setWidth(BattleshipGame.CELL_WIDTH * (length - 1));
            back.relocate((length - 1) * BattleshipGame.CELL_WIDTH, 0);
        } else {
            front.relocate(0, 0);
            middle.relocate(0, BattleshipGame.CELL_WIDTH / 2);
            middle.setWidth(BattleshipGame.CELL_WIDTH);
            middle.setHeight(BattleshipGame.CELL_WIDTH * (length - 1));
            back.relocate(0, (length - 1) * BattleshipGame.CELL_WIDTH);
        }
    }
}