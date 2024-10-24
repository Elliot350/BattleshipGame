package com.example.battleship;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlayerDoors extends Pane {

    private static final double TOP_DOOR_OPEN_Y = -315;
    private static final double TOP_DOOR_OPEN_X = -15;
    private static final double BOTTOM_DOOR_OPEN_Y = 600;
    private static final double BOTTOM_DOOR_OPEN_X = -15;

    private static final double TOP_DOOR_CLOSED_Y = 0;
    private static final double TOP_DOOR_CLOSED_X = -15;
    private static final double BOTTOM_DOOR_CLOSED_Y = 300;
    private static final double BOTTOM_DOOR_CLOSED_X = -15;

//    private final Rectangle topDoor;
//    private final Rectangle bottomDoor;

    private final Door topDoor;
    private final Door bottomDoor;

    private final Timeline closeTimeline;
    private final Timeline openTimeline;

    public PlayerDoors() {
        this(true);
    }

    public PlayerDoors(boolean open) {

        topDoor = new Door();
        bottomDoor = new Door();
        bottomDoor.setRotate(180);

//        topDoor = new Rectangle(630, 315, Color.LIGHTGRAY);
//        topDoor.setStroke(Color.LIGHTCYAN);
//        topDoor.setStrokeWidth(10);
//        bottomDoor = new Rectangle(630, 315, Color.LIGHTGRAY);
//        bottomDoor.setStroke(Color.LIGHTCYAN);
//        bottomDoor.setStrokeWidth(10);

        setMouseTransparent(true);
        setOpen(true);

        closeTimeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(topDoor.layoutYProperty(), TOP_DOOR_OPEN_Y),
                        new KeyValue(bottomDoor.layoutYProperty(), BOTTOM_DOOR_OPEN_Y)
                ),
                new KeyFrame(
                        Duration.millis(1500),
                        event -> setOpen(false),
                        new KeyValue(topDoor.layoutYProperty(), TOP_DOOR_CLOSED_Y),
                        new KeyValue(bottomDoor.layoutYProperty(), BOTTOM_DOOR_CLOSED_Y)
                ),
                new KeyFrame(
                        Duration.millis(2000)
                )
        );

        openTimeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(topDoor.layoutYProperty(), TOP_DOOR_CLOSED_Y),
                        new KeyValue(bottomDoor.layoutYProperty(), BOTTOM_DOOR_CLOSED_Y)
                ),
                new KeyFrame(
                        Duration.millis(1500),
                        event -> setOpen(true),
                        new KeyValue(topDoor.layoutYProperty(), TOP_DOOR_OPEN_Y),
                        new KeyValue(bottomDoor.layoutYProperty(), BOTTOM_DOOR_OPEN_Y)
                ),
                new KeyFrame(
                        Duration.millis(2000)
                )
        );

        getChildren().addAll(topDoor, bottomDoor);
        setOpen(open);
    }

    public void setOpen(boolean open) {
        if (open) {
            topDoor.setLayoutX(TOP_DOOR_OPEN_X);
            topDoor.setLayoutY(TOP_DOOR_OPEN_Y);
            bottomDoor.setLayoutX(BOTTOM_DOOR_OPEN_X);
            bottomDoor.setLayoutY(BOTTOM_DOOR_OPEN_Y);
        } else {
            topDoor.setLayoutX(TOP_DOOR_CLOSED_X);
            topDoor.setLayoutY(TOP_DOOR_CLOSED_Y);
            bottomDoor.setLayoutX(BOTTOM_DOOR_CLOSED_X);
            bottomDoor.setLayoutY(BOTTOM_DOOR_CLOSED_Y);
        }
    }

    public Timeline getCloseTimeline() {
        return closeTimeline;
    }

    public Timeline getOpenTimeline() {
        return openTimeline;
    }

    private static class Door extends Pane {

        private final Rectangle doorPane;
        private final Rectangle doorEdge;

        public Door() {
            doorPane = new Rectangle(630, 315);
            doorPane.setFill(Color.LIGHTGRAY);
            doorPane.setStrokeWidth(0);

            doorEdge = new Rectangle(630, 10);
            doorEdge.setFill(Color.CYAN);
            doorEdge.setStroke(Color.LIGHTCYAN);
            doorEdge.setStrokeWidth(3);
            doorEdge.relocate(0, 300);

            getChildren().addAll(doorPane, doorEdge);
        }
    }
}

