package com.example.battleship.ship;

import com.example.battleship.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Ship {

    private static final boolean labelSegments = true;

    private ArrayList<ShipSegment> segments;

    public Ship(int length, Direction direction) {
        if (length < 2) length = 2;
        segments = new ArrayList<>();

        BowSegment bowSegment = new BowSegment(direction);
        if (labelSegments) bowSegment.getChildren().add(createLabel(1));
        segments.add(bowSegment);

        for (int i = 0; i < length - 2; i++) {
            MiddleSegment middleSegment = new MiddleSegment(direction);
            if (labelSegments) middleSegment.getChildren().add(createLabel(i + 2));
            segments.add(middleSegment);
        }

        SternSegment sternSegment = new SternSegment(direction);
        if (labelSegments) sternSegment.getChildren().add(createLabel(length));
        segments.add(sternSegment);

        segments = new ArrayList<>(segments.reversed());
    }

    public ArrayList<ShipSegment> getSegments() {
        return segments;
    }

    private Label createLabel(int i) {
        Label label = new Label(Integer.toString(i));
        label.setTextFill(Color.RED);
        return label;
    }
}
