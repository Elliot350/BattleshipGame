package com.example.battleship.gameaction;

import com.example.battleship.BattleshipGame;
import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GridItem;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Dialog;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ReceiveShotAction extends AnimationAction {

    private final BattleshipPlayer player;
    private final int col;
    private final int row;

    public ReceiveShotAction(BattleshipPlayer player, int col, int row) {
        this.player = player;
        this.col = col;
        this.row = row;
        Rectangle rectangle = new Rectangle(5, 5);
        rectangle.relocate(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH / 2, 0);
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        event -> player.getPlayerBoard().getChildren().add(rectangle)
                ),
                new KeyFrame(
                        Duration.millis(1000),
                        new KeyValue(rectangle.layoutXProperty(), GridItem.convertX(col)),
                        new KeyValue(rectangle.layoutYProperty(), GridItem.convertY(row))
                )
        );
        timeline.setOnFinished(event -> player.getPlayerBoard().getChildren().remove(rectangle));
        length = timeline.getKeyFrames().getLast().getTime().toMillis();
    }

    @Override
    public String toString() {
        return "Receive shot at " + col + ", " + row + " on " + player;
    }
}
