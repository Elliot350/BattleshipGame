package com.example.battleship;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameDisplay extends Pane {

    private static final boolean NEED_CONFIRMATION = true;

    private final Pane confirmScreen;
    private final Pane winScreen;
    private final PlayerDoors confirmDoors;
    private Node current;
    private BattleshipPlayer targetPlayer;
    private final Button confirmButton;
    private final Label winLabel;

    public GameDisplay() {
        confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> continueToPlayer());

        confirmDoors = new PlayerDoors();
        confirmDoors.setOpen(false);

        confirmScreen = new StackPane(confirmDoors, confirmButton);
        winLabel = new Label();
        winScreen = new StackPane(new PlayerDoors(false), new Rectangle(100, 50, Color.WHITE), winLabel);
    }

    public void setDisplay(Node node) {
        getChildren().remove(current);
        getChildren().add(node);
        current = node;
    }

    public void switchPlayerTo(BattleshipPlayer targetPlayer) {
        this.targetPlayer = targetPlayer;
        targetPlayer.getDoors().setOpen(false);
        if (NEED_CONFIRMATION) {
            confirmButton.setText("Continue to " + targetPlayer);
            setDisplay(confirmScreen);
        } else {
            continueToPlayer();
        }
    }

    public void showWinner(BattleshipPlayer player) {
        winLabel.setText(player.toString() + " wins!");
        setDisplay(winScreen);
    }

    public void continueToPlayer() {
        setDisplay(targetPlayer);
        targetPlayer = null;
//        action.continueToPlayer();
    }

    public BattleshipPlayer getPlayer() {
        return current instanceof BattleshipPlayer ? (BattleshipPlayer) current : null;
    }
}
