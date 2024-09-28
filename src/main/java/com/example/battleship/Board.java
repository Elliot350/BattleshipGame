package com.example.battleship;

import javafx.scene.layout.Pane;

public abstract class Board extends Pane {

    abstract void placeMiss(int col, int row);
    abstract void placeHit(int col, int row);

}
