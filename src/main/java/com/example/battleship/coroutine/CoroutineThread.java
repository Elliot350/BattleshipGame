package com.example.battleship.coroutine;

import java.util.ArrayList;
import java.util.List;

public class CoroutineThread extends Thread {

    private final List<CoroutineStep> steps;

    public CoroutineThread(List<CoroutineStep> steps) {
        this.steps = new ArrayList<>(steps);
    }

    @Override
    public void run() {
        while (!steps.isEmpty()) {
            CoroutineStep currentStep = steps.removeFirst();
            currentStep.run();
            while (!currentStep.isDone()) {}
        }
    }
}
