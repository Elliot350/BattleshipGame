package com.example.battleship;

import javafx.scene.paint.Color;

public class MissIndicator extends ShotIndicator {

    public MissIndicator(int col, int row) {
        getChildren().add(createIndicator(Color.WHITE));
        moveToGridPosition(col, row);
    }
}
