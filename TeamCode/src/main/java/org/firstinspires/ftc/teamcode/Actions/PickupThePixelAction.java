package org.firstinspires.ftc.teamcode.Actions;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;

public class PickupThePixelAction implements IAction {
    BigRobotArmData armData;
    boolean pickupRight;
    boolean pickupLeft;
    boolean isFinished;
    double rightTarget;
    double leftTarget;
    public PickupThePixelAction(BigRobotArmData armData, boolean pickupRight, boolean pickupLeft) {
        this.armData = armData;
        this.pickupRight = pickupRight;
        this.pickupLeft = pickupLeft;
        rightTarget = armData.getClawServoRight().getPosition();
        leftTarget = armData.getClawServoLeft().getPosition();
    }

    @Override
    public void start() {
        if(pickupRight) {
            rightTarget = 0;
            armData.getClawServoRight().setPosition(rightTarget);

        }
        if(pickupLeft) {
            leftTarget = 1;
            armData.getClawServoLeft().setPosition(leftTarget);
        }
        isFinished = true;
    }

    @Override
    public void update() {
        if(!isFinished) return;
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
}
