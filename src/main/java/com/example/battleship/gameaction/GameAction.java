package com.example.battleship.gameaction;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Collection;
import java.util.function.Consumer;

public abstract class GameAction {

    private SequentialGameAction source;
    private Runnable whenDone = ActionManager::takeNextGameAction;
    protected AddAction addAction = ActionManager::addGameActions;
    protected AddAction addImmediateAction = ActionManager::addImmediateGameActions;

    public GameAction() {}

    public GameAction(Runnable whenDone) {
        setWhenDone(whenDone);
    }

    public abstract void perform();

    public void setSource(SequentialGameAction source) {
        this.source = source;
        setWhenDone(source::nextAction);
        addAction = source::addActions;
        addImmediateAction = source::addActionsToStart;
    }

    public void setWhenDone(Runnable runnable) {
        this.whenDone = runnable;
    }

    public void finishAction(double delayTime) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(delayTime),
                        event -> whenDone.run()
                )
        );
        timeline.play();
    }

    public void finishAction() {
        whenDone.run();
    }
}
