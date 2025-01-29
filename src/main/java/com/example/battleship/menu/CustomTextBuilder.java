package com.example.battleship.menu;

import javafx.scene.paint.Color;

public class CustomTextBuilder {

    private final static String DEFAULT_FONT_NAME = "OCR A Extended";
    private final static int DEFAULT_FONT_SIZE = 24;
    private final static Color DEFAULT_TEXT_COLOUR = Color.BLACK;

    public enum TextStyle {
        DEFAULT,
        TITLE
    }

    private String text;
    private String fontName = DEFAULT_FONT_NAME;
    private int fontSize = DEFAULT_FONT_SIZE;
    private Color textColour = DEFAULT_TEXT_COLOUR;

    public CustomTextBuilder() {}

    public CustomTextBuilder(TextStyle style) {
        setTextStyle(style);
    }

    public CustomTextBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public CustomTextBuilder setFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public CustomTextBuilder setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public CustomTextBuilder setTextColour(Color textColour) {
        this.textColour = textColour;
        return this;
    }

    public CustomTextBuilder setTextStyle(TextStyle style) {
        switch (style) {
            case DEFAULT -> {
                setFontSize(DEFAULT_FONT_SIZE);
                setFontName(DEFAULT_FONT_NAME);
                setTextColour(DEFAULT_TEXT_COLOUR);
            }
            case TITLE -> {
                setFontSize(2 * DEFAULT_FONT_SIZE);
                setFontName(DEFAULT_FONT_NAME);
                setTextColour(DEFAULT_TEXT_COLOUR);
            }
        }
        return this;
    }

    public CustomText createCustomText() {
        return new CustomText(text, fontName, fontSize, textColour);
    }
}