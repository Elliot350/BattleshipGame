package com.example.battleship;

import com.example.battleship.gameaction.SwitchPlayerAction;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.nio.Buffer;

public class GameDisplay extends Pane {

    private static final boolean NEED_CONFIRMATION = true;

    private final Pane confirmScreen;
    private Node current;
    private BattleshipPlayer targetPlayer;
    private SwitchPlayerAction action;

    public GameDisplay() {
        confirmScreen = new VBox();
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> {
            continueToPlayer();
        });
        confirmScreen.getChildren().add(confirmButton);
    }

    public void setDisplay(Node node) {
        getChildren().remove(current);
        getChildren().add(node);
        current = node;
    }

    public void takeAction(SwitchPlayerAction action) {
        this.action = action;
        targetPlayer = action.getPlayer();
        if (NEED_CONFIRMATION) {
            setDisplay(confirmScreen);
        } else {
            continueToPlayer();
        }
    }

    public void continueToPlayer() {
        setDisplay(targetPlayer);
        targetPlayer = null;
        action.continueToPlayer();
    }
}
