package com.example.battleship.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CustomButtonBuilder {

    private static final Background DEFAULT_BACKGROUND = new Background(
            new BackgroundFill(
                    Color.rgb(57, 155, 215),
                    CornerRadii.EMPTY,
                    Insets.EMPTY
            )
    );
    private static final Border DEFAULT_BORDER = new Border(
            new BorderStroke(
                    Color.rgb(43, 119, 164),
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(2.5)
            )
    );
    private static final Background HOVER_BACKGROUND = new Background(
            new BackgroundFill(
                    Color.rgb(65, 175, 242),
                    CornerRadii.EMPTY,
                    Insets.EMPTY
            )
    );
    private static final Border HOVER_BORDER = new Border(
            new BorderStroke(
                    Color.rgb(47, 134, 186),
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(2.5)
            )
    );

    public enum ButtonStyle {
        DEFAULT,
        PLAIN
    }

    private String text;
    private EventHandler<ActionEvent> onAction;
    private Node graphic;
    private Background defaultBackground;
    private Border defaultBorder;
    private Background hoverBackground;
    private Border hoverBorder;
    private Background heldBackground;
    private Border heldBorder;
    private CustomText customText;

    public CustomButtonBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public CustomButtonBuilder setText(CustomText text) {
        return setText("").setGraphic(text);
    }

    public CustomButtonBuilder setOnAction(EventHandler<ActionEvent> onAction) {
        this.onAction = onAction;
        return this;
    }

    public CustomButtonBuilder setGraphic(Node graphic) {
        this.graphic = graphic;
        return this;
    }

    public CustomButtonBuilder setDefaultBackground(Background defaultBackground) {
        this.defaultBackground = defaultBackground;
        return this;
    }

    public CustomButtonBuilder setDefaultBorder(Border defaultBorder) {
        this.defaultBorder = defaultBorder;
        return this;
    }

    public CustomButtonBuilder setHoverBackground(Background hoverBackground) {
        this.hoverBackground = hoverBackground;
        return this;
    }

    public CustomButtonBuilder setHoverBorder(Border hoverBorder) {
        this.hoverBorder = hoverBorder;
        return this;
    }

    public CustomButtonBuilder setHeldBackground(Background heldBackground) {
        this.heldBackground = heldBackground;
        return this;
    }

    public CustomButtonBuilder setHeldBorder(Border heldBorder) {
        this.heldBorder = heldBorder;
        return this;
    }

    public CustomButtonBuilder setStyle(ButtonStyle style) {
        switch (style) {
            case DEFAULT -> {
                setDefaultBackground(DEFAULT_BACKGROUND);
                setDefaultBorder(DEFAULT_BORDER);
                setHoverBackground(HOVER_BACKGROUND);
                setHoverBorder(HOVER_BORDER);
                setHeldBorder(null);
                setHeldBackground(null);
            }
            case PLAIN -> {
                setDefaultBackground(DEFAULT_BACKGROUND);
                setDefaultBorder(DEFAULT_BORDER);
                setHoverBorder(null);
                setHoverBackground(null);
                setHeldBorder(null);
                setHeldBackground(null);
            }
        }
        return this;
    }

    public CustomButton createCustomButton() {
        return new CustomButton(text, onAction, graphic, defaultBackground, defaultBorder, hoverBackground, hoverBorder, heldBackground, heldBorder);
    }
}