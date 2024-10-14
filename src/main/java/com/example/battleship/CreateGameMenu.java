package com.example.battleship;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class CreateGameMenu extends VBox {

    private GameDisplay display;
    private String firstName;
    private String secondName;

    public CreateGameMenu(GameDisplay display) {
        this.display = display;
        HBox nameEntries = new HBox(
                new NameEntry(true, string -> firstName = string),
                new NameEntry(false, string -> secondName = string)
        );
        getChildren().add(nameEntries);

        Button button = new Button("Start Game");
        button.setOnAction(event -> createGame());
        getChildren().add(button);
    }

    private void createGame() {
        BattleshipGame game = new BattleshipGame(display, firstName, secondName);
        game.startGame();
    }


    private static class NameEntry extends VBox {
        public NameEntry(boolean isFirstPerson, Consumer<String> setName) {
            getChildren().add(new Label("Enter " + (isFirstPerson ? "first" : "second") + " player name:"));
            TextField textField = new TextField();
            textField.textProperty().addListener((observable, oldValue, newValue) -> setName.accept(newValue));
            getChildren().add(textField);
            textField.setText("Player " + (isFirstPerson ? "1" : "2"));
        }
    }
}
