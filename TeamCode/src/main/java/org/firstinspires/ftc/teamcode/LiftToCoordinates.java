package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Actions.IAction;

public class LiftToCoordinates implements IAction {
    boolean isOver;
    @Override
    public void start() {

    }

    @Override
    public void update() {
        if(isOver) return;

    }

    @Override
    public boolean isOver() {
        return isOver;
    }
}
