/*package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotSensorData;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotDriveData;

public class BigRobotManualDrive implements IDecisionModule {
    BigRobotDriveData bigRobotDriveData;

    BigRobotSensorData bigRobotSensorData;
    Gamepad gamepad1;

    Values values;

    public BigRobotManualDrive(HardwareMap map, Gamepad gamepad1) {
        bigRobotDriveData = new BigRobotDriveData(map);
        bigRobotSensorData = new BigRobotSensorData(map);
        this.gamepad1 = gamepad1;
        values = new Values(this.gamepad1);
    }
    @Override
    public void controlLoop() {
        double x = values.getX();
        double y = values.getY();
        double turn = values.getTurn();

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
        if (bigRobotSensorData.getDistance() < 30)
            gamepad1.rumble(100);
    }
}

class Values{
    final double ACCEL_CONSTANT = 0.2;
    final double DECEL_CONSTANT = 0.2;
    double x;
    double y;
    double turn;

    Gamepad pad;
    public Values (Gamepad pad){
        this.pad = pad;
    }
    public void loop(){
        x = increaseValue(x,pad.left_stick_x);
        y = increaseValue(y,-pad.left_stick_y);
        turn = increaseValue(turn,pad.right_stick_x);

        if(pad.left_stick_x == 0) {
            x = decreaseValue(x,0);
        }
        if(pad.left_stick_y == 0) y = decreaseValue(y,0);
        if(pad.right_stick_x == 0) turn = decreaseValue(turn, 0);

        TelemetryHelper.getTelemetry().addData("x", x);
        TelemetryHelper.getTelemetry().addData("y", y);
        TelemetryHelper.getTelemetry().addData("turn", turn);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getTurn() {
        return turn;
    }
    public double increaseValue(double value, double max){
        double newValue;
        if (value*Math.signum(value) < max*Math.signum(max)){
            newValue=outOfRange(value + max*ACCEL_CONSTANT);
        } else{
            newValue = outOfRange(value);
        }
        return newValue;
    }
    public double decreaseValue(double value,double min){
        double newValue;
        double sign = Math.signum(value);
        if (value*sign > min*Math.signum(min)){
            newValue = (value - DECEL_CONSTANT)*sign;
        } else{
            newValue = value;
        }
        return newValue;
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

 */
package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;


import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotDriveData;
import org.firstinspires.ftc.teamcode.additional.DataPackages.BigRobotSensorData;

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

//        x = gamepad1.left_stick_x;
//        y = -gamepad1.left_stick_y;
//        turn = gamepad1.right_stick_x;

        x += gamepad1.left_stick_x*0.1;
        y -= gamepad1.left_stick_y*0.1;
        turn += gamepad1.right_stick_x*0.1;
        x = outOfRange(x);
        y = outOfRange(y);
        turn = outOfRange(turn);

        if(gamepad1.left_stick_x == 0) x = 0;
        if(gamepad1.left_stick_y == 0) y = 0;
        if(gamepad1.right_stick_x == 0) turn = 0;

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