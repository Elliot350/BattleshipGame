package com.example.battleship.gameaction;

import javafx.animation.Timeline;

public class AnimationAction extends GameAction {

    protected Timeline timeline;
    protected double length;

    public AnimationAction() {}

    public AnimationAction(Timeline timeline) {
        this(timeline, timeline.getKeyFrames().getLast().getTime().toMillis());
    }

    public AnimationAction(Timeline timeline, double length) {
        this.timeline = timeline;
        this.length = length;
    }

    @Override
    public void perform() {
        timeline.play();
        finishAction(length);
    }

    @Override
    public String toString() {
        return "Animation";
    }
}
