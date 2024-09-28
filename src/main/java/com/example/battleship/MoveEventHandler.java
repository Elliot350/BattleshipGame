package com.example.battleship;

public interface MoveEventHandler {
    void handle(int oldCol, int oldRow, int newCol, int newRow);
}
