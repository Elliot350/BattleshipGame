package com.example.battleship.gameaction;

import com.example.battleship.BattleshipGame;
import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GridItem;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MissileAnimationAction extends AnimationAction {
    public MissileAnimationAction(BattleshipPlayer player, int col, int row) {
        Rectangle rectangle = new Rectangle(5, 5);
        rectangle.relocate(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH / 2, BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        event -> player.getChildren().add(rectangle)
                ),
                new KeyFrame(
                        Duration.millis(1000),
                        event -> player.getChildren().remove(rectangle),
                        new KeyValue(rectangle.layoutXProperty(), GridItem.convertX(col)),
                        new KeyValue(rectangle.layoutYProperty(), GridItem.convertY(row))
                )
        );
//        timeline.onFinishedProperty().addListener((observable, oldValue, newValue) -> {
//            player.getChildren().remove(rectangle);
//        });
        setTimeline(timeline);
    }
}
