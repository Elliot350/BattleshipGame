package com.example.battleship;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Testing extends Application {
    @Override
    public void start(Stage stage) {
        GameDisplay display = new GameDisplay();
        Scene scene = new Scene(display, 600, 600);

        BattleshipGame battleshipGame = new BattleshipGame(display);
        battleshipGame.startGame();
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
