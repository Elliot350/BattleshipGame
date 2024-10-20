package com.example.battleship.gameaction;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class WaitAction extends GameAction {

    private boolean done;
    private final Timeline timeline;

    public WaitAction(double time) {
        done = false;
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(time),
                        event -> done = true
                )
        );
    }

    @Override
    public void doAction() {
        timeline.play();
    }

    @Override
    public boolean done() {
        return done;
    }
}
