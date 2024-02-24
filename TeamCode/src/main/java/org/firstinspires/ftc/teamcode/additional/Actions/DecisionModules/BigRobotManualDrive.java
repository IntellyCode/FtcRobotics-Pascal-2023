package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.Components.Sensors.BigRobotSensorData;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotDriveData;

public class BigRobotManualDrive implements IDecisionModule {
    BigRobotDriveData bigRobotDriveData;

    BigRobotSensorData bigRobotSensorData;
    Gamepad gamepad1;

    double x = 0;
    double y = 0;
    double turn;

    public BigRobotManualDrive(HardwareMap map, Gamepad gamepad1) {
        bigRobotDriveData = new BigRobotDriveData(map);
        bigRobotSensorData = new BigRobotSensorData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Data reading

        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
/*
        x += gamepad1.left_stick_x*0.25;
        y += gamepad1.left_stick_y*0.25;
        turn += gamepad1.right_stick_x*0.25;
        x = outOfRange(x);
        y = outOfRange(y);
        turn = outOfRange(turn);


         */
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
       if(bigRobotSensorData.getDistance() < 30)
            gamepad1.rumble(100);


    }

    private double outOfRange(double value){
        if (value > 1){
            return 1;
        }
        if (value < -1){
            return -1;
        }
        return value;
    }
}