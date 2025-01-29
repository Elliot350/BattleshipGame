package com.example.battleship.menu;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomText extends Label {

    public CustomText(String text, String fontName, int fontSize, Color textColour) {
        setText(text);
        setFont(Font.font(fontName, fontSize));
        setTextFill(textColour);
    }
}
