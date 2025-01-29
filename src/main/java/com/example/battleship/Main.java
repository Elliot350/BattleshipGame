package com.example.battleship;

import com.example.battleship.menu.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GameDisplay gameDisplay = new GameDisplay();
        Scene scene = new Scene(gameDisplay, 600, 600);

        MainMenu mainMenu = new MainMenu(gameDisplay);
        gameDisplay.setDisplay(mainMenu);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}