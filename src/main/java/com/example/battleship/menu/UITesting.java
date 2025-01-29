package com.example.battleship.menu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UITesting extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);

        CustomButton button = new CustomButtonBuilder()
                .setText(new CustomTextBuilder(CustomTextBuilder.TextStyle.DEFAULT)
                        .setText("Press Me!")
                        .createCustomText()
                )
                .setOnAction(event -> System.out.println("Yay!"))
                .setStyle(CustomButtonBuilder.ButtonStyle.DEFAULT)
                .createCustomButton();


        CustomText title = new CustomTextBuilder(CustomTextBuilder.TextStyle.TITLE)
                .setText("Title")
                .createCustomText();
        CustomText normal = new CustomTextBuilder(CustomTextBuilder.TextStyle.DEFAULT)
                .setText("Normal")
                .createCustomText();

        CustomText coloured = new CustomTextBuilder()
                .setText("WOW!")
                .setTextColour(Color.BLUE)
                .createCustomText();

//        VBox fontPane = new VBox();
//        fontPane.setAlignment(Pos.CENTER);
//        ScrollPane pane = new ScrollPane(fontPane);
//        List<String> fonts = Font.getFontNames();
//        System.out.println(fonts.size());
//        for (String font : fonts) {
//            CustomText text = new CustomTextBuilder()
//                    .setText(font)
//                    .setFontName(font)
//                    .setFontSize(36)
//                    .createCustomText();
//            fontPane.getChildren().add(text);
//        }
//        root.getChildren().addAll(pane);

        root.getChildren().addAll(title, normal, coloured, button);


        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Button Testing");
        stage.setScene(scene);
        stage.show();
    }
}
