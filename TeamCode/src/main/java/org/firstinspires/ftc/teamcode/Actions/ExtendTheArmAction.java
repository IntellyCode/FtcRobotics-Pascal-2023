package org.firstinspires.ftc.teamcode.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.DataPackages.BigRobotArmData;
import org.firstinspires.ftc.teamcode.TelemetryHelper;

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

        targetTicks = bigRobotArmData.getUpperArmMotor().getCurrentPosition () +
                (int) (deltaExtension*bigRobotArmData.ticksPerCm);

        bigRobotArmData.getUpperArmMotor().setTargetPosition(targetTicks);
        bigRobotArmData.getUpperArmMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bigRobotArmData.getUpperArmMotor().setPower(1);
    }

    @Override
    public void update() {
        if(isFinished) return;
        if(closelyEqual(bigRobotArmData.getUpperArmMotor().getCurrentPosition(), targetTicks, 10))
            isFinished = true;
        TelemetryHelper.getTelemetry().addData("currentticks", bigRobotArmData.getUpperArmMotor().getCurrentPosition());
        TelemetryHelper.getTelemetry().addData("targetticks", targetTicks);
    }

    @Override
    public boolean isOver() {
        return isFinished;
    }
    boolean closelyEqual(double o1, double o2, double uncertainty) {
        return (o1 > o2-uncertainty) && (o1 < o2+uncertainty);
    }

}
