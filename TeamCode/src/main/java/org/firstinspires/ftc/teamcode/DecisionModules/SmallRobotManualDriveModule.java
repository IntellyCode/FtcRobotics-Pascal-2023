package org.firstinspires.ftc.teamcode.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.DataPackages.SmallRobotDriveData;

public class SmallRobotManualDriveModule implements IDecisionModule {
    SmallRobotDriveData smallRobotDriveData;
    Gamepad gamepad;

    double x;
    double y;
    double turn;

    public SmallRobotManualDriveModule(HardwareMap map, Gamepad gamepad) {
        smallRobotDriveData = new SmallRobotDriveData(map);
        this.gamepad = gamepad;
    }
    @Override
    public void controlLoop() {
        //Data reading
        x = gamepad.left_stick_x;
        y = -gamepad.left_stick_y;
        turn = gamepad.right_stick_x;

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
        smallRobotDriveData.getUpperLeft().setPower(ulPower / denominator);
        smallRobotDriveData.getLowerLeft().setPower(llPower / denominator);
        smallRobotDriveData.getUpperRight().setPower(urPower / denominator);
        smallRobotDriveData.getLowerRight().setPower(lrPower / denominator);


    }
}

