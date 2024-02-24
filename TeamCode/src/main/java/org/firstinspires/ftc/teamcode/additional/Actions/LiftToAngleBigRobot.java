package org.firstinspires.ftc.teamcode.additional.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;

public class LiftToAngleBigRobot implements IAction {
    BigRobotArmData bigRobotArmData;
    boolean isFinished;
    double targetAngle;
    int targetTicks;
    public LiftToAngleBigRobot(BigRobotArmData bigRobotArmData, double targetAngle) {
        this.bigRobotArmData = bigRobotArmData;
        this.targetAngle = targetAngle;
    }

    @Override
    public void start() {
        double deltaAngle = targetAngle - bigRobotArmData.currentAngle;
        targetTicks = (int) (bigRobotArmData.ticksPerDeg*deltaAngle);
        bigRobotArmData.getLowerArmMotor().setTargetPosition(targetTicks);
        bigRobotArmData.getLowerArmMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bigRobotArmData.getLowerArmMotor().setPower(0.4);
    }
    @Override
    public void update() {
        if(isFinished) return;
        if((closelyEqual(bigRobotArmData.getLowerArmMotor().getCurrentPosition(), targetTicks, 10))) {
            isFinished = true;
        }
    }
    @Override
    public boolean isOver() {
        return isFinished;
    }



    boolean closelyEqual(double o1, double o2, double uncertainty) {
        return (o1 > o2-uncertainty) && (o1 < o2+uncertainty);
    }
}
