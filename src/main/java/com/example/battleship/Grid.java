package com.example.battleship;

import java.util.ArrayList;
import java.util.List;

// TODO: Do I add generics in the class or not?
public class Grid {

    private final GridItem[][] items;

    public Grid(int cols, int rows) {
        items = new GridItem[cols][rows];
    }

    public GridItem set(int col, int row, GridItem item) {
        return set(col, row, item, false);
    }

    public GridItem set(int col, int row, GridItem item, boolean move) {
        if (outsideGrid(col, row)) return null;
        GridItem overwrittenItem = items[col][row];
        items[col][row] = item;
        if (move) item.moveToGridPosition(col, row);
        return overwrittenItem;
    }

    public GridItem get(int col, int row) {
        if (outsideGrid(col, row)) return null;
        return items[col][row];
    }

    public boolean containsSomething(int col, int row) {
        if (outsideGrid(col, row)) return false;
        return items[col][row] != null;
    }

    public boolean outsideGrid(int col, int row) {
        return col < 0 || col >= items.length || row < 0 || row >= items[0].length;
    }
}
