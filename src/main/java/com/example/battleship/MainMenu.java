package com.example.battleship;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainMenu extends VBox {

    private GameDisplay display;

    public MainMenu(GameDisplay display) {
        this.display = display;

        getChildren().add(new Label("Battleship"));
        Button button = new Button("Start Game");
        button.setOnAction(event -> startCreatingGame());
        getChildren().add(button);
    }

    public void startCreatingGame() {
        CreateGameMenu gameMenu = new CreateGameMenu(display);
        display.setDisplay(gameMenu);
    }
}
