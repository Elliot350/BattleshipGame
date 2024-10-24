package com.example.battleship.gameaction;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AnimationAction extends StartAnimationAction {

    private boolean done;

    public AnimationAction() {
        super();
        done = false;
    }

    public AnimationAction(Timeline timeline) {
        super(timeline);
        done = false;
    }

    @Override
    protected void setTimeline(Timeline timeline) {
        super.setTimeline(timeline);
        this.timeline.setOnFinished(event -> done = true);
    }

    @Override
    public boolean done() {
        return done;
    }
}
