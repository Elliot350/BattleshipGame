package com.example.battleship.gameaction;

import com.example.battleship.BattleshipGame;
import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GridItem;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ReceiveShotAction extends InstantAction {

    private final BattleshipPlayer player;
    private final int row;
    private final int col;

    public ReceiveShotAction(BattleshipPlayer player, int col, int row) {
        this.player = player;
        this.col = col;
        this.row = row;
    }

    @Override
    public void doAction() {
        Rectangle rectangle = new Rectangle(5, 5);
        rectangle.relocate(BattleshipGame.CELL_WIDTH * BattleshipGame.BOARD_WIDTH / 2, 0);
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        event -> player.getPlayerBoard().getChildren().add(rectangle)
                ),
                new KeyFrame(
                        Duration.millis(1000),
                        event -> player.getPlayerBoard().getChildren().remove(rectangle),
                        new KeyValue(rectangle.layoutXProperty(), GridItem.convertX(col)),
                        new KeyValue(rectangle.layoutYProperty(), GridItem.convertY(row))
                )
        );
        GameManager.addToStart(
                new WaitAction(1000),
                new AnimationAction(timeline),
                new SimpleGameAction(() -> {
                    if (player.isHit(col, row)) {
                        player.getPlayerBoard().placeHit(col, row);
                    } else {
                        player.getPlayerBoard().placeMiss(col, row);
                    }
                })
        );
    }
}
