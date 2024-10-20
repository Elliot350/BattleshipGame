package com.example.battleship.gameaction;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AnimationAction extends GameAction {

    private boolean done;
    private Timeline timeline;

    public AnimationAction() {
        done = false;
    }

    public AnimationAction(Timeline timeline) {
        done = false;
        setTimeline(timeline);
    }

    protected void setTimeline(Timeline timeline) {
        this.timeline = timeline;
        this.timeline.setOnFinished(event -> done = true);
//        double lastKeyFrame = timeline.getKeyFrames().getLast().getTime().toMillis();
//        this.timeline.getKeyFrames().add(new KeyFrame(
//                Duration.millis(lastKeyFrame + 1),
//                event -> done = true
//        ));
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
