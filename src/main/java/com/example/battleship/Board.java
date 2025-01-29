package com.example.battleship;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public abstract class Board extends Pane {

    protected static final Color backgroundColour = Color.LIGHTBLUE;



    abstract void placeMiss(int col, int row);
    abstract void placeHit(int col, int row);
}
