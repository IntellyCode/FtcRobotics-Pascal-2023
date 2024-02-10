package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.additional.DataPackages.DriveData;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;

public class ManualDriveModule implements IDecisionModule {
    DriveData driveData;
    Gamepad gamepad1;

    double x;
    double y;
    double turn;

    public ManualDriveModule(HardwareMap map, Gamepad gamepad1) {
        driveData = new DriveData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Data reading
        x = -gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
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
        driveData.upperLeft.setPower(ulPower / denominator);
        driveData.lowerLeft.setPower(llPower / denominator);
        driveData.upperRight.setPower(urPower / denominator);
        driveData.lowerRight.setPower(lrPower / denominator);
    }
}

