package org.firstinspires.ftc.teamcode.additional.Actions;

import org.firstinspires.ftc.teamcode.additional.DataPackages.ArmData;

public class LiftToPosition implements IAction {
    ArmData armData;
    LiftToAngle angleAction;
    double targetYCoordinate;
    boolean isStarted;
    boolean isFinished;
    public LiftToPosition(double yCoordinate, ArmData armData) {
        this.armData = armData;
        this.targetYCoordinate = yCoordinate;
    }

    @Override
    public void start() {
        double targetAngle = calculateTargetAngle(targetYCoordinate);
        LiftToAngle liftToAngle = new LiftToAngle(armData, targetAngle);
        liftToAngle.start();
        isStarted = true;
    }
    @Override
    public void update() {
        if(angleAction.isOver())
            isFinished = true;
    }
    @Override
    public boolean isOver() {
        return isFinished;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    double calculateTargetAngle(double yCoordinate) {
        return Math.asin(yCoordinate*armData.gearRatio);
    }
}
