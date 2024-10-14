package com.example.battleship.ship;

import com.example.battleship.GridItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ShipSegment extends GridItem {
    protected static final Color SHIP_COLOUR = Color.NAVY;

    private boolean isShot = false;

    public void getShot() {
        isShot = true;
    }

    public boolean isShot() {
        return isShot;
    }

    @Override
    public String toString() {
        return "■■■■";
    }
}
