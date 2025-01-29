package com.example.battleship.menu;

import com.example.battleship.GameDisplay;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Menu extends VBox {

    protected final GameDisplay display;

    public Menu(GameDisplay display) {
        super(10);
        this.display = display;
        setAlignment(Pos.CENTER);
    }
}
