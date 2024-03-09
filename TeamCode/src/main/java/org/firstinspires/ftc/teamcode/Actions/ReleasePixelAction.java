package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;

public class ReleasePixelAction implements IAction {
    BigRobotArmData armData;
    boolean releaseRight;
    boolean releaseLeft;
    boolean isFinished;
    double rightTarget;
    double leftTarget;

    public ReleasePixelAction(BigRobotArmData armData, boolean releaseRight, boolean releaseLeft) {
        this.armData = armData;
        this.releaseRight = releaseRight;
        this.releaseLeft = releaseLeft;
        rightTarget = armData.getClawServoRight().getPosition();
        leftTarget = armData.getClawServoLeft().getPosition();
    }

    @Override
    public void start() {
        if(releaseRight) {
            rightTarget = 1;
            armData.getClawServoRight().setPosition(rightTarget);

        }
        if(releaseLeft) {
            leftTarget = 0;
            armData.getClawServoLeft().setPosition(leftTarget);
        }
    }

    @Override
    public void update() {
        if(!isFinished) return;
        if((armData.getClawServoLeft().getPosition() == leftTarget) && (armData.getClawServoRight().getPosition() == rightTarget))
            isFinished = true;
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
