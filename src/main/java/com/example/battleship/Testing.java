package com.example.battleship;

import com.example.battleship.gameaction.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Testing extends Application {
    @Override
    public void start(Stage stage) {
        GameDisplay display = new GameDisplay();
        Scene scene = new Scene(display, 600, 600);

//        Scene actionListScene = new Scene(GameManager.getActionList(), 200, 600);
//        Stage actionListStage = new Stage();
//        actionListStage.setX(0);
//        stage.setOnCloseRequest(event -> actionListStage.close());
//        actionListStage.setScene(actionListScene);
//        actionListStage.show();

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
