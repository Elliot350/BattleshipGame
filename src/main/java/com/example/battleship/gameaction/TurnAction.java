package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;
import com.example.battleship.GameDisplay;

import java.util.ArrayList;
import java.util.List;

public class TurnAction extends SequentialGameAction {

    private final BattleshipPlayer player;
    private final GameDisplay gameDisplay;
    private final List<GameAction> initialActions;
    private TurnAction nextTurn;

    public TurnAction(BattleshipPlayer player, GameDisplay gameDisplay) {
        super();
        this.player = player;
        this.gameDisplay = gameDisplay;
        initialActions = new ArrayList<>();
    }

    public void addInitialActions(GameAction... actions) {
        for (GameAction action : actions) {
            action.setWhenDone(this::nextAction);
            this.initialActions.add(action);
        }
    }

    @Override
    protected void nextAction() {
        currentAction = null;
        actions.addAll(0, toAddActions);
        toAddActions.clear();
        if (!initialActions.isEmpty()) {
            currentAction = initialActions.removeFirst();
            currentAction.perform();
        } else if (!actions.isEmpty()) {
            currentAction = actions.removeFirst();
            currentAction.perform();
        } else {
            ActionManager.addImmediateGameActions(nextTurn);
            finishAction();
        }
    }

    @Override
    public void perform() {
        nextTurn = new TurnAction(player.getOpponent(), gameDisplay);
        addActions(
                new AimAction(player, this),
                new SwitchPlayerAction(gameDisplay, player.getOpponent())
        );
        super.perform();
    }

    public TurnAction getNextTurn() {
        return nextTurn;
    }
}
