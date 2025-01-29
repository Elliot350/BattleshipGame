package com.example.battleship.menu;

import com.example.battleship.BattleshipGame;
import com.example.battleship.GameDisplay;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.function.Consumer;

public class CreateGameMenu extends Menu {

    private String firstName;
    private String secondName;

    public CreateGameMenu(GameDisplay display) {
        super(display);
        HBox nameEntries = new HBox(10,
                new NameEntry(true, string -> firstName = string),
                new NameEntry(false, string -> secondName = string)
        );
        nameEntries.setAlignment(Pos.CENTER);
        getChildren().add(nameEntries);

        CustomButton button = new CustomButtonBuilder()
                .setGraphic(
                        new CustomTextBuilder(CustomTextBuilder.TextStyle.DEFAULT)
                                .setText("Start Game")
                                .createCustomText()
                )
                .setOnAction(event -> createGame())
                .setStyle(CustomButtonBuilder.ButtonStyle.DEFAULT)
                .createCustomButton();

        getChildren().add(button);
    }

    private void createGame() {
        BattleshipGame game = new BattleshipGame(display, firstName, secondName);
        game.startGame();
    }


    private static class NameEntry extends VBox {
        public NameEntry(boolean isFirstPerson, Consumer<String> setName) {
            super(10);
            CustomText text = new CustomTextBuilder(CustomTextBuilder.TextStyle.DEFAULT)
                    .setText("Enter "+ (isFirstPerson ? "first" : "second") + " player name:")
                    .setFontSize(16)
                    .createCustomText();
            TextField textField = new TextField();
            textField.setFont(
                    Font.font("OCR A Extended", 16)
            );
            getChildren().add(text);
            textField.textProperty().addListener((observable, oldValue, newValue) -> setName.accept(newValue));
            getChildren().add(textField);
            textField.setText("Player " + (isFirstPerson ? "1" : "2"));
        }
    }
}
