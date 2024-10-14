package com.example.battleship;

import com.example.battleship.gameaction.WaitForConfirmationAction;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameDisplay extends Pane {

    private static final boolean NEED_CONFIRMATION = true;

    private final Pane confirmScreen;
    private final PlayerDoors confirmDoors;
    private Node current;
    private BattleshipPlayer targetPlayer;
    private WaitForConfirmationAction action;
    private final Button confirmButton;

    public GameDisplay() {
        confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> continueToPlayer());

        confirmDoors = new PlayerDoors();
        confirmDoors.setOpen(false);

        confirmScreen = new StackPane(confirmDoors, confirmButton);
    }

    public void setDisplay(Node node) {
        getChildren().remove(current);
        getChildren().add(node);
        current = node;
    }

    public void takeAction(WaitForConfirmationAction action) {
        this.action = action;
        targetPlayer = action.getPlayer();
        if (NEED_CONFIRMATION) {
            confirmButton.setText("Continue to " + targetPlayer);
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
