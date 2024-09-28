package com.example.battleship;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GridItem extends Pane {

    private List<MoveEventHandler> moveEventHandlers;

    protected int col;
    protected int row;

    public GridItem() {
        this(0, 0);
    }

    public GridItem(int col, int row) {
        moveEventHandlers = new ArrayList<>();
        this.col = col;
        this.row = row;
        relocate(convertX(col), convertY(row));
    }

    public void moveToGridPosition(int col, int row) {
        // Ignore any movement that doesn't do anything
        // TODO: Should this be here? Do I want to trigger when moved to the same spot?
        if (this.col == col && this.row == row) return;
        relocate(convertX(col), convertY(row));
        for (MoveEventHandler handler : moveEventHandlers) handler.handle(this.col, this.row, col, row);
        this.col = col;
        this.row = row;
    }

    public void addMoveEventHandler(MoveEventHandler moveEventHandler) {
        moveEventHandlers.add(moveEventHandler);
    }

    public void removeMoveEventHandler(MoveEventHandler moveEventHandler) {
        moveEventHandlers.remove(moveEventHandler);
    }

    public static double convertX(int col) {
        // Accounting for the grid overlapping
        return col * (BattleshipGame.CELL_WIDTH) - 1;
    }

    public static double convertY(int row) {
        // Accounting for the grid overlapping
        return row * (BattleshipGame.CELL_WIDTH) - 1;
    }
}
