package com.example.battleship.coroutine;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class Coroutine extends Thread {

    private boolean started;
    private boolean running;
    private List<CoroutineStep> steps;

    public Coroutine(CoroutineStep... steps) {
        started = false;
        running = false;
        this.steps = new ArrayList<>();
        addSteps(steps);
    }

    private void addSteps(CoroutineStep... steps) {
        if (started || running) return;
        for (CoroutineStep step : steps) {
            if (!this.steps.contains(step)) this.steps.add(step);
        }
    }

    public Coroutine whenDone(Coroutine... next) {
        for (Coroutine coroutine : next) {
            for (CoroutineStep coroutineStep : coroutine.steps) {
                addSteps(coroutineStep);
            }
        }
        return this;
    }

    @Override
    public void run() {
        started = true;
        running = true;
        while (!steps.isEmpty()) {
            CoroutineStep currentStep = steps.removeFirst();
            currentStep.run();
            while (!currentStep.isDone()) {}
        }
        running = false;
    }

    public static Coroutine createSetupCoroutine(BattleshipPlayer player) {
        return new Coroutine(
                new SimpleCoroutineStep(() -> {
                    player.setState(BattleshipPlayer.PlayerState.PLACING_SHIPS);
                    Platform.runLater(player::startPlacingShips);
                }),
                new WaitUntilStep(() -> player.getState() != BattleshipPlayer.PlayerState.PLACING_SHIPS),
                new SimpleCoroutineStep(() -> System.out.println("Done placing ships!"))
        );
    }

    public static Coroutine createSwitchPlayerCoroutine(GameDisplay display, BattleshipPlayer currentPlayer) {
        return new Coroutine(
                new SimpleCoroutineStep(() -> System.out.println("Switching player!")),
                new AnimationCoroutine(currentPlayer.getDoors().getCloseTimeline()),
                new SimpleCoroutineStep(() -> {
                    currentPlayer.getOpponent().getDoors().setOpen(false);
                    Platform.runLater(() -> display.switchToPlayer(currentPlayer.getOpponent()));
                }),
                new WaitUntilStep(() -> display.getCurrentPlayer() == currentPlayer.getOpponent()),
                new AnimationCoroutine(currentPlayer.getOpponent().getDoors().getOpenTimeline())
        );
    }
}
