package com.example.battleship;

import com.example.battleship.coroutine.Coroutine;
import com.example.battleship.coroutine.SimpleCoroutineStep;
import com.example.battleship.coroutine.WaitUntilStep;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CoroutineTesting {

    public static long time;
    public static void main(String[] args) {
        time = System.currentTimeMillis();

        Coroutine coroutine = new Coroutine(
                new SimpleCoroutineStep(() -> System.out.println("Start")),
                new WaitUntilStep(() -> System.currentTimeMillis() - time >= 2500),
                new SimpleCoroutineStep(() -> System.out.println("Done"))
        );

        coroutine.start();
    }
}
