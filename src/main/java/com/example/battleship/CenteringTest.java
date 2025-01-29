package com.example.battleship;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CenteringTest extends Application {
    @Override
    public void start(Stage stage) {
        GameDisplay gameDisplay = new GameDisplay();
        Scene scene = new Scene(gameDisplay, 600, 600);

        Rectangle rectangle = new Rectangle(50, 50);
        Label label = new Label("Test");
        label.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK,
                                BorderStrokeStyle.SOLID,
                                CornerRadii.EMPTY,
                                BorderWidths.DEFAULT
                        )
                )
        );
        VBox vBox = new VBox(rectangle, label);
        vBox.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(10)
                )
        ));
        vBox.setAlignment(Pos.CENTER);
        gameDisplay.setDisplay(vBox);
//        gameDisplay.getChildren().addAll(rectangle, label);
//        root.getChildren().addAll(rectangle, label);

//        gameDisplay.setDisplay(rectangle);

        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        launch();
    }
}
