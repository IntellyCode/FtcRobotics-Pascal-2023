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
        if(targetAngle > 45)
            targetAngle = 45;
        else if(targetAngle < 0)
            targetAngle = 0;
        double deltaAngle = targetAngle - bigRobotArmData.currentAngle;
        targetTicks = bigRobotArmData.getLowerArmMotor().getCurrentPosition() + (int) (bigRobotArmData.ticksPerDeg*deltaAngle);
        bigRobotArmData.getLowerArmMotor().setTargetPosition(targetTicks);
        bigRobotArmData.getLowerArmMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bigRobotArmData.getLowerArmMotor().setPower(0.5);
    }
    @Override
    public void update() {
        TelemetryHelper.getTelemetry().addData("From liftaction: targetAngle", targetAngle);
        TelemetryHelper.getTelemetry().addData("From liftaction: target ticks", targetTicks);
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
