package org.firstinspires.ftc.teamcode.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;

public class ExtendTheArmAction implements IAction {
    BigRobotArmData bigRobotArmData;
    double targetLength;
    int targetTicks;
    boolean isFinished;

    public ExtendTheArmAction(BigRobotArmData bigRobotArmData, double targetLength) {
        this.bigRobotArmData = bigRobotArmData;
        this.targetLength = targetLength;
    }
    @Override
    public void start() {
        double deltaExtension = targetLength - bigRobotArmData.armLength;
        targetTicks = (int) (deltaExtension*bigRobotArmData.ticksPerCm);
        bigRobotArmData.getUpperArmMotor().setTargetPosition(targetTicks);
        bigRobotArmData.getUpperArmMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bigRobotArmData.getUpperArmMotor().setPower(1);
    }

    @Override
    public void update() {
        if(isFinished) return;
        if(bigRobotArmData.getUpperArmMotor().getCurrentPosition() == targetTicks)
            isFinished = true;
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }

}
