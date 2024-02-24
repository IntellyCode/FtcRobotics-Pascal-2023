package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotDriveData;

public class BigRobotManualDrive implements IDecisionModule {
    BigRobotDriveData bigRobotDriveData;
    Gamepad gamepad1;

    double x;
    double y;
    double turn;

    public BigRobotManualDrive(HardwareMap map, Gamepad gamepad1) {
        bigRobotDriveData = new BigRobotDriveData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Data reading
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;

        TelemetryHelper.getTelemetry().addData("x", x);
        TelemetryHelper.getTelemetry().addData("y", y);
        TelemetryHelper.getTelemetry().addData("turn", turn);

        //Movement
        double ulPower = y + x + turn;
        double llPower = y - x + turn;
        double urPower = y - x - turn;
        double lrPower = y + x - turn;
        TelemetryHelper.getTelemetry().addData("ulPower", ulPower);
        TelemetryHelper.getTelemetry().addData("llPower", llPower);
        TelemetryHelper.getTelemetry().addData("urPower", urPower);
        TelemetryHelper.getTelemetry().addData("lrPower", lrPower);

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);
        TelemetryHelper.getTelemetry().addData("fp", ulPower / denominator);
        bigRobotDriveData.getUpperLeft().setPower(ulPower / denominator);
        bigRobotDriveData.getLowerLeft().setPower(llPower / denominator);
        bigRobotDriveData.getUpperRight().setPower(urPower / denominator);
        bigRobotDriveData.getLowerRight().setPower(lrPower / denominator);
        TelemetryHelper.getTelemetry().addData("Enabled:", bigRobotDriveData.getLowerLeft().isMotorEnabled());

        //Haptic feedback
        if(bigRobotDriveData.distanceSensor.getDistance(DistanceUnit.CM) < 30)
            gamepad1.rumble(100);
    }
}