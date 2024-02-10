package org.firstinspires.ftc.teamcode.additional.Actions;

import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;

public class LiftToPosition implements IAction {
    SmallRobotArmData smallRobotArmData;
    LiftToAngle angleAction;
    double targetYCoordinate;
    boolean isStarted;
    boolean isFinished;
    public LiftToPosition(double yCoordinate, SmallRobotArmData smallRobotArmData) {
        this.smallRobotArmData = smallRobotArmData;
        this.targetYCoordinate = yCoordinate;
    }

    @Override
    public void start() {
        double targetAngle = calculateTargetAngle(targetYCoordinate);
        LiftToAngle liftToAngle = new LiftToAngle(smallRobotArmData, targetAngle);
        liftToAngle.start();
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

    double calculateTargetAngle(double yCoordinate) {
        //return Math.asin(yCoordinate* smallRobotArmData.gearRatio);
        return 0;
    }
}
