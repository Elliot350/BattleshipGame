package com.example.battleship.menu;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

public class CustomButton extends Button {
    CustomButton(
            String text,
            EventHandler<ActionEvent> onAction,
            Node graphic,
            Background defaultBackground,
            Border defaultBorder,
            Background hoverBackground,
            Border hoverBorder,
            Background heldBackground,
            Border heldBorder
    ) {
        setText(text);
        setOnAction(onAction);
        setGraphic(graphic);
        setBackground(defaultBackground);
        setBorder(defaultBorder);
        hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (hoverBackground != null) {
                setBackground(newValue ? hoverBackground : defaultBackground);
            }
            if (hoverBorder != null) {
                setBorder(newValue ? hoverBorder : defaultBorder);
            }
        });
        pressedProperty().addListener((observable, oldValue, newValue) -> {
            if (heldBackground != null) {
                setBackground(newValue ? heldBackground : defaultBackground);
            }
            if (heldBorder != null) {
                setBorder(newValue ? heldBorder : defaultBorder);
            }

        });
    }
}
