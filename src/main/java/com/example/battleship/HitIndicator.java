package com.example.battleship;

import javafx.scene.paint.Color;

public class HitIndicator extends ShotIndicator {

    public HitIndicator(int col, int row) {
        getChildren().add(createIndicator(Color.RED));
        moveToGridPosition(col, row);
    }
}
