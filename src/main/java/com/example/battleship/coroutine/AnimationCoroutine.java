package com.example.battleship.coroutine;

import javafx.animation.Timeline;

public class AnimationCoroutine extends CoroutineStep {

    private final Timeline timeline;
    private boolean done;

    public AnimationCoroutine(Timeline timeline) {
        this.timeline = timeline;
        timeline.setOnFinished(event -> done = true);
        done = false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void run() {
        timeline.play();
    }
}
