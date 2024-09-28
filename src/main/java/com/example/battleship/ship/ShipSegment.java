package com.example.battleship.ship;

import com.example.battleship.GridItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ShipSegment extends GridItem {
    protected static final Color SHIP_COLOUR = Color.NAVY;

    public void getShot() {

    }

    @Override
    public String toString() {
        return "■■■■";
    }
}
