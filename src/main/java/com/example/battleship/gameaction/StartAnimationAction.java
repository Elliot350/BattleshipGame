package com.example.battleship.gameaction;

import javafx.animation.Timeline;

public class StartAnimationAction extends InstantAction {

    protected Timeline timeline;

    public StartAnimationAction() {

    }

    public StartAnimationAction(Timeline timeline) {
        setTimeline(timeline);
    }

    protected void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @Override
    public void doAction() {
        timeline.play();
    }
}
