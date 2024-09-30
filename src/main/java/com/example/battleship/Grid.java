package com.example.battleship;

public class Grid<T extends GridItem> {

    private final GridItem[][] items;
    private final Class<T> tClass;

    public Grid(int cols, int rows, Class<T> tClass) {
        items = new GridItem[cols][rows];
        this.tClass = tClass;
    }

    public T set(int col, int row, T item) {
        return set(col, row, item, false);
    }

    public T set(int col, int row, T item, boolean moveItem) {
        if (outsideGrid(col, row)) return null;
        GridItem overwrittenItem = items[col][row];
        items[col][row] = item;
        if (moveItem) item.moveToGridPosition(col, row);
        return tClass.cast(overwrittenItem);
    }

    public T get(int col, int row) {
        if (outsideGrid(col, row)) return null;
        return tClass.cast(items[col][row]);
    }

    public boolean containsSomething(int col, int row) {
        if (outsideGrid(col, row)) return false;
        return items[col][row] != null;
    }

    public boolean outsideGrid(int col, int row) {
        return col < 0 || col >= items.length || row < 0 || row >= items[0].length;
    }
}
