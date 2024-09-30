package com.example.battleship.gameaction;

import com.example.battleship.BattleshipPlayer;

public class AimAction extends PlayerGameAction {

    private TurnAction turnAction;

    public AimAction(BattleshipPlayer player, TurnAction turnAction) {
        super(player);
        this.turnAction = turnAction;
    }

    public void takeShot(int col, int row) {
        turnAction.addImmediateActions(
                new MissileAnimation(player, col, row),
                new RegisterHitAction(player.getOpponent(), col, row)
        );
        finishAction(100);
    }

    @Override
    public void perform() {
        player.startAiming(this);
    }

    @Override
    public String toString() {
        return player + " aim";
    }
}
