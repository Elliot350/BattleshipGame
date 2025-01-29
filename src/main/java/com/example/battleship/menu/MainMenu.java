package com.example.battleship.menu;

import com.example.battleship.GameDisplay;

public class MainMenu extends Menu {

    public MainMenu(GameDisplay display) {
        super(display);

//        CustomButton startButton = new CustomButtonBuilder()
//                .setText("Start Game")
//                .setOnAction(event -> startCreatingGame())
//                .setStyle(CustomButtonBuilder.ButtonStyle.DEFAULT)
//                .createCustomButton();

        CustomText title = new CustomTextBuilder(CustomTextBuilder.TextStyle.TITLE)
                .setText("Battleship")
                .createCustomText();

        CustomButton startButton = new CustomButtonBuilder()
                .setText(new CustomTextBuilder(CustomTextBuilder.TextStyle.DEFAULT)
                        .setText("Start Game")
                        .createCustomText()
                ).setOnAction(event -> startCreatingGame())
                .setStyle(CustomButtonBuilder.ButtonStyle.DEFAULT)
                .createCustomButton();

        getChildren().addAll(title, startButton);
    }

    public void startCreatingGame() {
        CreateGameMenu gameMenu = new CreateGameMenu(display);
        display.setDisplay(gameMenu);
    }
}
