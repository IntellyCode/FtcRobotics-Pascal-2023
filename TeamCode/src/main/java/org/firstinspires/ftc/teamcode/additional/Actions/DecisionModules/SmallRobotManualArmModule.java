package org.firstinspires.ftc.teamcode.additional.Actions.DecisionModules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.TelemetryHelper;
import org.firstinspires.ftc.teamcode.additional.DataPackages.SmallRobotArmData;

public class SmallRobotManualArmModule implements IDecisionModule {
    Gamepad gamepad1;
    SmallRobotArmData smallRobotArmData;

    double upperArmY;
    double lowerArmY;
    double cleshnjaY;
    public SmallRobotManualArmModule(HardwareMap map, Gamepad gamepad1) {
        smallRobotArmData = new SmallRobotArmData(map);
        this.gamepad1 = gamepad1;
    }
    @Override
    public void controlLoop() {
        //Upper arm
        upperArmY = gamepad1.right_trigger - gamepad1.left_trigger;

        smallRobotArmData.getLowerServo().setPosition(0);
        smallRobotArmData.getGrabberServo().setPosition(0);
        smallRobotArmData.getRightArmMotor().setPower(upperArmY);
        smallRobotArmData.getLeftArmMotor().setPower(upperArmY);

        //Lower arm
        if(gamepad1.dpad_up && lowerArmY < 1)
            lowerArmY += 0.01;
        if(gamepad1.dpad_down && lowerArmY > 0)
            lowerArmY -= 0.01;
        smallRobotArmData.getLowerServo().setPosition(lowerArmY);

        //Cleshnja
        if(gamepad1.left_bumper && cleshnjaY > 0) cleshnjaY -= 0.01*0.5;
        if(gamepad1.right_bumper && cleshnjaY < 1) cleshnjaY += 0.01*0.5;
        smallRobotArmData.getGrabberServo().setPosition(cleshnjaY);
        TelemetryHelper.getTelemetry().addData("CleshnjaY:", cleshnjaY);
        TelemetryHelper.getTelemetry().addData("Servo pos", smallRobotArmData.getGrabberServo().getPosition());

    }
}
